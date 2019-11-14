package com.greco.service.impl;

import com.greco.exception.BadRequestException;
import com.greco.messages.GenericCheckingMessage;
import com.greco.service.UploadService;
import com.greco.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    @Override
    public void uploadFile(MultipartFile file, String folder) throws Exception {
        //Create the directory if it is necessary
        Utils.createDirectory(folder);
        try {
            // Get the file and save it on UPLOADED_FOLDER
            byte[] bytes = file.getBytes();
            //Filename -> [userId]+[timestamp]+[filename]
            Path path = Paths.get(folder + file.getOriginalFilename());
            Files.write(path, bytes);
        }catch (IOException e) {
            e.printStackTrace();
            throw new Exception(GenericCheckingMessage.SOLAR_PANEL_UPLOAD_PROBLEMS.toString());
        }
    }
}
