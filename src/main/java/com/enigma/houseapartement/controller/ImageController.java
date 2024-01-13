package com.enigma.houseapartement.controller;

import com.enigma.houseapartement.dto.CommondResponse;
import com.enigma.houseapartement.entity.Image;
import com.enigma.houseapartement.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/image")
public class ImageController {
    private final ImageService imageService;

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> createImage(@RequestPart("file")MultipartFile[] files) {
//        List<Image> responImage = imageService.create(files);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                CommondResponse.builder()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .message("Success create image")
//                        .data(responImage)
//                        .build()
//        );
//    }
}
