package com.ksprogramming.equipment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements FileStorageServiceInterface {
    public String saveImageOnServer(MultipartFile file) {
        try {
            String fileStorageLocation = "./src/main/resources/static/img/picture";
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(fileStorageLocation, fileName);
            Files.write(path, file.getBytes());
            return fileName;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
