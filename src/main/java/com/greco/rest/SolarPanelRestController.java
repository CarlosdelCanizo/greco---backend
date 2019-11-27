package com.greco.rest;

import com.greco.dtos.CoordinatesDto;
import com.greco.exception.BadRequestException;
import com.greco.exception.ForbiddenException;
import com.greco.exception.NotFoundException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Comment;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private CommentService commentService;

    private String PROJECTION_NAME = "solarPanel";

    @GetMapping("{id}")
    public IProjectable byId(@PathVariable("id") Long id) {
        IProjectable result =  Projection.convertSingle(solarPanelService.findById(id), "solarPanel");
        if(authenticationService.getLoggedUser() != null)
                fillAdditionalFields((com.greco.model.projection.SolarPanel)result, authenticationService.getLoggedUser().getUserId());
        return result;
    }

    @GetMapping("{id}/comments")
    public Page<IProjectable> getSolarPanelComments(@PathVariable("id") Long id,
                                                    @RequestParam(value="page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                                    @RequestParam(value="size", required = false, defaultValue = DEFAULT_SIZE) int size) {
        Pageable sortedByCreationDateAsc = PageRequest.of(page, size, Sort.by("creationDate").ascending());
        Page<Comment> comments = commentService.findBySolarPanelId(id, sortedByCreationDateAsc);
        return (Page<IProjectable>)Projection.convert(comments, "comment");
    }

    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = SolarPanel.class)})
    @GetMapping
    public Page<IProjectable> search(@RequestParam(value="q", required = false, defaultValue = "") String search,
                                     @RequestParam(value="page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(value="size", required = false, defaultValue = DEFAULT_SIZE) int size,
                                     @RequestParam(value="sort", required = false, defaultValue = DEFAULT_SORT) String sort,
                                     @RequestParam(value="field", required = false, defaultValue = "id") String field,
                                     @RequestBody(required = false) CoordinatesDto coordinatesDto) {
        Page<IProjectable> result;
        if(coordinatesDto == null || coordinatesDto.getLat() == null || coordinatesDto.getLon() == null)
            result = getByCustomSearch(search, page, size, sort, field);
        else
            result = getByCoordinates(coordinatesDto, page, size);
        return result;
    }

    @PutMapping
    public IProjectable update(@RequestBody SolarPanel solarPanel) {
        checkSolarPanelValues(solarPanel);
        checkIfLoggedInUserHasPermissions(solarPanelService.findById(solarPanel.getId()).getRegistrationSolarPanel().getOwner().getUserId(), GenericCheckingMessage.FORBIDDEN_ACTION.toString());
        IProjectable result =  Projection.convertSingle(solarPanelService.update(solarPanel), PROJECTION_NAME);
        ((com.greco.model.projection.SolarPanel)result).setIsMine(true); // If the solar panel is updated it is because the loggedUser is the owner
        return result;
    }

    @PostMapping
    public IProjectable insert(@RequestBody RegistrationSolarPanel registrationSolarPanel) {
        SolarPanel solarPanel = registrationSolarPanel.getSolarPanel();
        checkSolarPanelValues(solarPanel);
        registrationSolarPanel.setRegistrationDate(Utils.getTimestamp());
        registrationSolarPanel.setOwner(authenticationService.getLoggedUser());
        solarPanel = solarPanelService.insert(solarPanel);
        registrationSolarPanelService.insert(registrationSolarPanel);
        return Projection.convertSingle(solarPanelService.insert(solarPanel), PROJECTION_NAME);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        RegistrationSolarPanel registrationSolarPanel = registrationSolarPanelService.findBySolarPanelId(id);
        if(registrationSolarPanel == null)
            throw new NotFoundException(GenericCheckingMessage.ENTITY_NOT_FOUND.toString());
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

    private Page<IProjectable> getByCustomSearch(String search, int page, int size, String sort, String field) {
        Page<SolarPanel> solarPanelPage = solarPanelService.findAll(specs(search), pageRequest(page, size, sort, field));
        Page<IProjectable> result = getIProjectablePage(solarPanelPage);
        return result;
    }

    private Page<IProjectable> getIProjectablePage(Page<SolarPanel> solarPanelPage) {
        Page<IProjectable> result = (Page<IProjectable>)Projection.convert(solarPanelPage, PROJECTION_NAME);
        List<IProjectable> solarPanelProjections = result.getContent();
        for(Object solarPanelProjection : solarPanelProjections) {
            if(authenticationService.getLoggedUser() != null)
                fillAdditionalFields((com.greco.model.projection.SolarPanel)solarPanelProjection, authenticationService.getLoggedUser().getUserId()); //Fill with extra info
        }
        return result;
    }

    private Page<IProjectable> getByCoordinates(CoordinatesDto coordinatesDto, int page, int size) {
        double meters = coordinatesDto.getDistance()*1000;
        // number of km per degree = ~111km (111.32 in google maps, but range varies
        //between 110.567km at the equator and 111.699km at the poles)
        // 1km in degree = 1 / 111.32km = 0.0089
        // 1m in degree = 0.0089 / 1000 = 0.0000089
        double coefficient = meters * 0.0000089;
        double maxLatitude = getLatitudeDependingOnCoefficient(coordinatesDto.getLat(),  coefficient);
        double minLatitude = getLatitudeDependingOnCoefficient(coordinatesDto.getLat(),  coefficient*-1);
        // pi / 180 = 0.018
        double maxLongitude = getLongitudeDependingOnCoefficient(coordinatesDto.getLon(), coordinatesDto.getLat(), coefficient);
        double minLongitude = getLongitudeDependingOnCoefficient(coordinatesDto.getLon(), coordinatesDto.getLat(), coefficient*-1);
//        System.out.println("maxLatitude: "+ maxLatitude);
//        System.out.println("minLatitude: "+minLatitude);
//        System.out.println("maxLongitude: "+ maxLongitude);
//        System.out.println("minLongitude: "+minLongitude);
        Pageable sortedByIdAsc = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<SolarPanel> solarPanels = solarPanelService.findByLatitudeAndLongitudeLimits(minLatitude, maxLatitude, minLongitude, maxLongitude, sortedByIdAsc);
        return  getIProjectablePage(solarPanels);
    }

    private double getLatitudeDependingOnCoefficient(Double lat, Double coefficient) {
        double latitudeResult = lat + coefficient;
        if(latitudeResult > 90)
            latitudeResult = 90;
        if(latitudeResult < -90)
            latitudeResult = -90;
        return latitudeResult;
    }

    private double getLongitudeDependingOnCoefficient(Double lon, Double lat, double coefficient) {
       double longitudeResult =  lon + coefficient / Math.cos(lat * 0.018);
        if(longitudeResult > 180)
            longitudeResult = 180;
        if(longitudeResult < -180)
            longitudeResult = -180;
        return longitudeResult;
    }

    private void fillAdditionalFields(com.greco.model.projection.SolarPanel solarPanelProjection, Long loggedInUserId) {
        if(solarPanelProjection.getRegistrationSolarPanel() != null && solarPanelProjection.getRegistrationSolarPanel().getOwner().getUserId().equals(loggedInUserId))
            solarPanelProjection.setIsMine(true);
    }

    private void checkSolarPanelValues(SolarPanel solarPanel) {
        checkLatitudeLimits(solarPanel.getLat());
        checkLongitudeLimits(solarPanel.getLon());
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

    private void checkLatitudeLimits(Double lat) {
        if(lat == null || lat.doubleValue() < -90 || lat.doubleValue() > 90)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_LATITUDE_WRONG_VALUES.toString());
    }

    private void checkLongitudeLimits(Double lon) {
        if(lon == null || lon.doubleValue() < -180 || lon.doubleValue() > 180)
            throw new BadRequestException(GenericCheckingMessage.SOLAR_PANEL_LONGITUDE_WRONG_VALUES.toString());
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
}
