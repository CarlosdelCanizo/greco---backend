package com.greco.rest;

import com.greco.exception.BadRequestException;
import com.greco.exception.ForbiddenException;
import com.greco.exception.NotFoundException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.model.Multimedia;
import com.greco.model.SolarPanel;
import com.greco.model.projection.IProjectable;
import com.greco.model.projection.Projection;
import com.greco.service.AuthenticationService;
import com.greco.service.MultimediaService;
import com.greco.service.SolarPanelService;
import com.greco.service.UploadService;
import com.greco.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("multimedia")
public class MultimediaRestController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MultimediaService multimediaService;
    @Autowired
    private SolarPanelService solarPanelService;

    private final String UPLOAD_FOLDER = "./uploads/";
    private final int MULTIMEDIA_MAXIMUM_NUM_CAN_BE_UPLOADED = 3;

    @PostMapping("/upload/{solarPanelId}")
    public IProjectable uploadMultimedia(@PathVariable("solarPanelId") Long solarPanelId,
                                         @RequestParam("file") MultipartFile file) throws Exception {
        // Check if user can upload the picture
        checkIfMultimediaCanBeUploaded(solarPanelId);
        // Upload the file
        String folderName = UPLOAD_FOLDER + authenticationService.getLoggedUser().getUserId() + "/" + solarPanelId + "/";
        uploadService.uploadFile(file, folderName);
        // Create multimedia register if it doesn't exist
        Multimedia multimediaCreated = createMultimedia(solarPanelId, file);
        return Projection.convertSingle(multimediaCreated, "multimedia");
    }

    @GetMapping("{id}/getImage/")
    public void getImage(HttpServletResponse response,
                         @PathVariable("id") Long id) throws Exception {
        Multimedia multimedia = multimediaService.findById(id);
        File file = getFileFromMultimedia(multimedia);
        InputStream targetStream = new FileInputStream(file);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(targetStream, response.getOutputStream());
    }

    @GetMapping("{id}/getImageB64/")
    public ResponseEntity<String> getImage(@PathVariable("id") Long id) {
        Multimedia multimedia = multimediaService.findById(id);
        File fitxer = getFileFromMultimedia(multimedia);
        if (!fitxer.exists()) {
            throw new NotFoundException("image.notfound");
        }

        byte[] image = new byte[0];
        try {
            image = Files.readAllBytes(fitxer.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Base64.getEncoder().encodeToString(image));
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        deleteMultimediaById(id);
    }

    private Multimedia createMultimedia(Long solarPanelId, MultipartFile file) {
        //If multimedia is already saved return existing multimedia
        String filename = file.getOriginalFilename();
        List<Multimedia> savedMultimedia = multimediaService.findBySolarPanelIdAndName(solarPanelId, filename);
        if(savedMultimedia.size() > 0)
            return savedMultimedia.get(0);
        // Else create new multimedia register
        Multimedia multimedia = new Multimedia();
        multimedia.setCreationDate(Utils.getTimestamp());
        multimedia.setSolarPanel(solarPanelService.findById(solarPanelId));
        multimedia.setName(filename);
        return multimediaService.insert(multimedia);
        //multimedia.setDescription("");
    }

    private void deleteMultimediaById(Long multimediaId) {
        // Delete file
        Multimedia multimedia = multimediaService.findById(multimediaId);
        deleteMultimedia(multimedia);
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

    private File getFileFromMultimedia(Multimedia multimedia) {
        String filename = multimedia.getName();
        String userFolder = multimediaService.getFolderNameFromMultimedia(multimedia);
        File file = new File(userFolder + filename);
        return file;
    }

    private void checkIfMultimediaCanBeUploaded(Long solarPanelId) {
        SolarPanel solarPanel = solarPanelService.findById(solarPanelId);
        // Check if logged in user is the owner of solar panel
        checkIfLoggedInUserHasPermissions(solarPanel.getRegistrationSolarPanel().getOwner().getUserId(), GenericCheckingMessage.SOLAR_PANEL_NOT_PERMISSION_TO_UPLOAD_MULTIMEDIA.toString());
        List<Multimedia> multimedia = solarPanel.getMultimedia();
        // Check the number of multimedia uploaded
        if(multimedia.size() >= MULTIMEDIA_MAXIMUM_NUM_CAN_BE_UPLOADED)
            throw new ForbiddenException(GenericCheckingMessage.SOLAR_PANEL_UPLOADED_MULTIMEDIA_EXCEEDED.toString());
    }

    private void checkIfLoggedInUserHasPermissions(Long ownerId, String message) {
        if(!ownerId.equals(authenticationService.getLoggedUser().getUserId()))
            throw new ForbiddenException(message);
    }
}
