package com.enigma.houseapartement.service;

import com.enigma.houseapartement.dto.ImageResponse;
import com.enigma.houseapartement.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image create(Image image);
    List<ImageResponse> getImageById(String id);
}
