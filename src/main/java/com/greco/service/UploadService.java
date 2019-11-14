package com.greco.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void uploadFile(MultipartFile file, String folder) throws Exception;
}
