package com.greco.rest;

import com.greco.exception.BadRequestException;
import com.greco.exception.ForbiddenException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Multimedia;
import com.greco.model.RegistrationSolarPanel;
import com.greco.model.SolarPanel;
import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.*;
import com.greco.utils.Utils;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("solarPanel")
public class SolarPanelRestController extends BaseRestController<SolarPanel>{
    @Autowired
    private SolarPanelService solarPanelService;
    @Autowired
    private MultimediaService multimediaService;
    @Autowired
    private RegistrationSolarPanelService registrationSolarPanelService;
    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("{id}")
    public IProjectable byId(@PathVariable("id") Long id) {
        IProjectable result =  Projection.convertSingle(solarPanelService.findById(id), "solarPanel");
        fillAdditionalFields((com.greco.model.projection.SolarPanel)result, authenticationService.getLoggedUser().getUserId());
        return result;
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SolarPanel.class)})
    @GetMapping
    public Page<IProjectable> search(@RequestParam(value="q", required = false, defaultValue = "") String search,
                                     @RequestParam(value="page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value="size", required = false, defaultValue = DEFAULT_SIZE) int size,
                                     @RequestParam(value="sort", required = false, defaultValue = DEFAULT_SORT) String sort,
                                     @RequestParam(value="field", required = false, defaultValue = "id") String field,
                                     @RequestParam(value="projection", required = false, defaultValue = "solarPanel") String projection) {
        Page<SolarPanel> solarPanelPage = solarPanelService.findAll(specs(search), pageRequest(page, size, sort, field));
        Page<IProjectable> result = (Page<IProjectable>)Projection.convert(solarPanelPage, projection);
        List<IProjectable> solarPanelProjections = result.getContent();
        for(Object solarPanelProjection : solarPanelProjections) {
            fillAdditionalFields((com.greco.model.projection.SolarPanel)solarPanelProjection, authenticationService.getLoggedUser().getUserId());
        }
        return result;
    }

    private void fillAdditionalFields(com.greco.model.projection.SolarPanel solarPanelProjection, Long loggedInUserId) {
        if(solarPanelProjection.getRegistrationSolarPanel() != null && solarPanelProjection.getRegistrationSolarPanel().getOwner().getUserId().equals(loggedInUserId))
            solarPanelProjection.setIsMine(true);
    }

    @PutMapping
    public IProjectable update(@RequestBody SolarPanel solarPanel) {
        checkSolarPanelValues(solarPanel);
        checkIfLoggedInUserHasPermissions(solarPanelService.findById(solarPanel.getId()).getRegistrationSolarPanel().getOwner().getUserId(), GenericCheckingMessage.FORBIDDEN_ACTION.toString());
        IProjectable result =  Projection.convertSingle(solarPanelService.update(solarPanel), "solarPanel");
        ((com.greco.model.projection.SolarPanel)result).setIsMine(true); // If the solar panel is updated it is because the loggedUser is the owner
        return result;
    }

    private void checkSolarPanelValues(SolarPanel solarPanel) {
        checkMandatoryValues(solarPanel);
        if(solarPanel.getElectricalCapacity() == null && solarPanel.getSurface() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_POWER_CAPACITY_OR_PANEL_SURFACE_MANDATORY.toString());
        if(solarPanel.getOrientation() == null || solarPanel.getOrientation() < 0 || solarPanel.getOrientation() > 360)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_ORIENTATION_WRONG_VALUES.toString());
        if(solarPanel.getInclination() == null || solarPanel.getInclination() < 0 || solarPanel.getInclination() > 90)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_INCLINATION_WRONG_VALUES.toString());
        if (solarPanel.getPanelTrackingOrientation().booleanValue() == false && solarPanel.getOrientation() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_WITH_TRACKING_ORIENTATION_MANDATORY.toString());
        if(solarPanel.getPanelTrackingInclination().booleanValue() == false && solarPanel.getInclination() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_WITH_TRACKING_INCLINATION_MANDATORY.toString());
    }

    private void checkMandatoryValues(SolarPanel solarPanel) {
        if(solarPanel.getLat() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_LATITUDE_MANDATORY.toString());
        if(solarPanel.getLon() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_LONGITUDE_MANDATORY.toString());
        if(solarPanel.getPanelTrackingOrientation() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_TRACKING_ORIENTATION_MANDATORY.toString());
        if(solarPanel.getPanelTrackingInclination() == null)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_TRACKING_INCLINATION_MANDATORY.toString());
    }

    @PostMapping
    public IProjectable insert(@RequestBody RegistrationSolarPanel registrationSolarPanel) {
        SolarPanel solarPanel = registrationSolarPanel.getSolarPanel();
        checkSolarPanelValues(solarPanel);
        registrationSolarPanel.setRegistrationDate(Utils.getTimestamp());
        registrationSolarPanel.setOwner(authenticationService.getLoggedUser());
        solarPanel = solarPanelService.insert(solarPanel);
        registrationSolarPanelService.insert(registrationSolarPanel);
        return Projection.convertSingle(solarPanelService.insert(solarPanel), "solarPanel");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        RegistrationSolarPanel registrationSolarPanel = registrationSolarPanelService.findBySolarPanelId(id);
        checkIfLoggedInUserHasPermissions(registrationSolarPanel.getOwner().getUserId(), GenericCheckingMessage.FORBIDDEN_ACTION.toString());
        SolarPanel solarPanel = registrationSolarPanel.getSolarPanel();
        List<Multimedia> multimediaList = solarPanel.getMultimedia();
        for(Multimedia multimedia : multimediaList) {
            deleteMultimedia(multimedia);
        }
        registrationSolarPanelService.deleteById(registrationSolarPanel.getId());
        //delete folder of solar panel
        String folder = solarPanelService.getFolderNameFromSolarPanel(solarPanel);
        Utils.deleteFolder(folder);
        solarPanelService.deleteById(solarPanel.getId());
    }

    private void deleteMultimedia(Multimedia multimedia) {
        // Delete file
        // Check if logged in user is the owner of multimedia
        checkIfLoggedInUserHasPermissions(multimedia.getSolarPanel().getRegistrationSolarPanel().getOwner().getUserId(), GenericCheckingMessage.FORBIDDEN_ACTION.toString());
        String multimediaFolder = multimediaService.getFolderNameFromMultimedia(multimedia);
        Utils.deleteFile(multimedia.getName(), multimediaFolder);
        // Delete multimedia register
        multimediaService.deleteById(multimedia.getId());
    }

    private void checkIfLoggedInUserHasPermissions(Long ownerId, String message) {
        if(!ownerId.equals(authenticationService.getLoggedUser().getUserId()))
            throw new ForbiddenException(message);
    }

//    private SolarPanel mockSolarPanel() {
//        SolarPanel solarPanel = new SolarPanel();
//        solarPanel.setCommissioningDate(new Date());
//        solarPanel.setElectricalCapacity(19.5);
//        solarPanel.setOrientation("south");
//        solarPanel.setInclination("45");
//        solarPanel.setLat("41.3818");
//        solarPanel.setLon("2.1685");
//        solarPanel.setMunicipality("Barcelona");
//        solarPanel.setPostcode("08041");
//        solarPanel.setTechnologyUsed("monocrystalline silicon");
//        return solarPanel;
//    }
}
