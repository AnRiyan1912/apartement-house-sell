package com.enigma.houseapartement.service.serviceImpl;

import com.enigma.houseapartement.dto.ImageResponse;
import com.enigma.houseapartement.entity.House;
import com.enigma.houseapartement.entity.Image;
import com.enigma.houseapartement.repository.ImageRepository;
import com.enigma.houseapartement.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image create(Image image) {

        return imageRepository.save(image);
    }

    @Override
    public List<ImageResponse> getImageById(String id) {
        List<Image> images =imageRepository.getImageByHouseId(id);

        return images.stream().map((image ->
                ImageResponse.builder()
                        .id(image.getId())
                        .fileName(image.getFileName())
                        .time(image.getFileName())
                        .build()
                )).toList();
    }

}
