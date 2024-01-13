package com.enigma.houseapartement.service.serviceImpl;

import com.enigma.houseapartement.dto.HouseRequest;
import com.enigma.houseapartement.dto.HouseResponse;
import com.enigma.houseapartement.dto.ImageResponse;
import com.enigma.houseapartement.entity.House;
import com.enigma.houseapartement.entity.Image;
import com.enigma.houseapartement.repository.HouseRepository;
import com.enigma.houseapartement.service.HouseService;
import com.enigma.houseapartement.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final FileStorageService fileStorageService;
    private final ImageService imageService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public HouseResponse create(HouseRequest houseRequest) {

        House saveHouse = House.builder()
                .name(houseRequest.getName())
                .city(houseRequest.getCity())
                .state(houseRequest.getState())
                .unit(houseRequest.getUnit())
                .wifi(houseRequest.getWifi())
                .laundry(houseRequest.getLaundry())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        houseRepository.saveAndFlush(saveHouse);
         List<Image> listImage = new ArrayList<>();
        Arrays.asList(houseRequest.getFile()).stream().forEach((file) -> {
            String contentType = fileStorageService.storageFile(file);
            Image saveImage = Image.builder()
                    .fileName(contentType)
                    .dateTime(LocalDateTime.now())
                    .house(saveHouse)
                    .build();
            imageService.create(saveImage);
            listImage.add(saveImage);
        });

        return HouseResponse.builder()
                .id(saveHouse.getId())
                .name(saveHouse.getName())
                .city(saveHouse.getCity())
                .state(saveHouse.getState())
                .unit(saveHouse.getUnit())
                .wifi(saveHouse.getWifi())
                .laundry(saveHouse.getLaundry())
                .images(listImage.stream().map((image ->
                        ImageResponse.builder()
                                .id(image.getId())
                                .fileName(image.getFileName())
                                .time(image.getDateTime().toString())
                                .build()
                        )).toList())
                .build();
    }

    @Override
    public List<HouseResponse> getAll() {
        List<House> houses = houseRepository.findAll();
        return null;

//       return houses.stream().map((house) -> {
//            HouseResponse.builder()
//                   .id(house.getId())
//                   .name(house.getName())
//                   .city(house.getCity())
//                   .state(house.getState())
//                   .unit(house.getUnit())
//                   .wifi(house.getWifi())
//                   .laundry(house.getLaundry())
//                   .images(house.getImage())
//                   .build();
//       }).toList();
    }

    @Override
    public HouseResponse getById(String id) {
        House house = houseRepository.getHouseById(id);
        List<ImageResponse>  imagesResponse = imageService.getImageById(id);
        if(house == null) {
            throw new NotFoundException("House not already exist");
        }

        return  HouseResponse.builder()
                .id(house.getId())
                .name(house.getName())
                .city(house.getCity())
                .state(house.getState())
                .unit(house.getUnit())
                .wifi(house.getWifi())
                .laundry(house.getLaundry())
                .images(imagesResponse)
                .build();
    }

    @Override
    public HouseResponse remove(String id) {
        return null;
    }
}
