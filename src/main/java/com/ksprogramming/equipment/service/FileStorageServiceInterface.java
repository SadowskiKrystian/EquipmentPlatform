package com.ksprogramming.equipment.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceInterface {
    String saveImageOnServer(MultipartFile file);
}
