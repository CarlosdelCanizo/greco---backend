package com.greco.rest;

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
import java.util.Date;
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
        return Projection.convertSingle(solarPanelService.findById(id), "solarPanel");
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SolarPanel.class)})
    @GetMapping
    public Page<IProjectable> search(@RequestParam(value="q", required = false, defaultValue = "") String search,
                                     @RequestParam(value="page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value="size", required = false, defaultValue = DEFAULT_SIZE) int size,
                                     @RequestParam(value="sort", required = false, defaultValue = DEFAULT_SORT) String sort,
                                     @RequestParam(value="field", required = false, defaultValue = "id") String field,
                                     @RequestParam(value="projection", required = false, defaultValue = "solarPanel") String projection) {
        return (Page<IProjectable>)Projection.convert(solarPanelService.findAll(specs(search), pageRequest(page, size, sort, field)),projection);
    }

    @PutMapping
    public IProjectable update(@RequestBody SolarPanel solarPanel) {
        return Projection.convertSingle(solarPanelService.update(solarPanel), "solarPanel");
    }

    @PostMapping
    public IProjectable insert(@RequestBody RegistrationSolarPanel registrationSolarPanel) {
        registrationSolarPanel.setRegistrationDate(Utils.getTimestamp());
        registrationSolarPanel.setOwner(authenticationService.getLoggedUser());
        SolarPanel solarPanel = solarPanelService.insert(registrationSolarPanel.getSolarPanel());
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

    private SolarPanel mockSolarPanel() {
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setCommissioningDate(new Date());
        solarPanel.setElectricalCapacity(19.5);
        solarPanel.setOrientation("south");
        solarPanel.setInclination("45");
        solarPanel.setLat("41.3818");
        solarPanel.setLon("2.1685");
        solarPanel.setMunicipality("Barcelona");
        solarPanel.setPostcode("08041");
        solarPanel.setTechnologyUsed("monocrystalline silicon");
        return solarPanel;
    }
}
