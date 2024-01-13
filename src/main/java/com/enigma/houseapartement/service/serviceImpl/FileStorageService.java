package com.enigma.houseapartement.service.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService {
    private final Path fileStorageLocation ;
    public FileStorageService(@Value("${app.house-apartement.pathSaveImage}") String pathLocation) {
        try{
            Files.createDirectories(this.fileStorageLocation = Paths.get(pathLocation));
        }catch (Exception e) {
            throw new RuntimeException("Could not create the directory");
        }
    }

    public String storageFile(MultipartFile file) {
        String mimeType = file.getContentType();
        if (mimeType == null || mimeType.startsWith("image/*")) {
            throw new RuntimeException("Invalid upload only image!");
        }
        try{
            String[] arrString = file.getContentType().split("/");
            String contentType = "";
            contentType = arrString[0] + "-" + Math.random() + "." + arrString[1];

            Path targerLocation = this.fileStorageLocation.resolve(contentType);
            Files.copy(file.getInputStream(), targerLocation, StandardCopyOption.REPLACE_EXISTING);
            return contentType;

        }catch (IOException e){
            throw new RuntimeException("Could not store " + file.getOriginalFilename()+ " please check agaim " + e);
        }
    }

    public Resource getFile(String fileName) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());
            if (resource.exists()) {
                return resource;
            }
            throw new NotFoundException("File not found " + fileName);
        }catch (MalformedURLException e) {
            throw new NotFoundException("File not found " + fileName);
        }
    }
}
