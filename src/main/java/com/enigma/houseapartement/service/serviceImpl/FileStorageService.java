package com.enigma.houseapartement.service.serviceImpl;

import com.enigma.houseapartement.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FileStorageService {

    private final Path fileStorageLocation = Paths.get("/home/user/BATCH14/spring boot/house-apartement/src/main/java/com/enigma/houseapartement/images/house");
    public FileStorageService( ) {
        try{
            Files.createDirectories(this.fileStorageLocation);
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
}
