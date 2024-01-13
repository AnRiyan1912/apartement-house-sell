package com.enigma.houseapartement.service.serviceImpl;

import com.enigma.houseapartement.dto.HouseRequest;
import com.enigma.houseapartement.dto.HouseResponse;
import com.enigma.houseapartement.dto.ImageResponse;
import com.enigma.houseapartement.entity.House;
import com.enigma.houseapartement.entity.HousePrice;
import com.enigma.houseapartement.entity.Image;
import com.enigma.houseapartement.repository.HouseRepository;
import com.enigma.houseapartement.service.HousePriceService;
import com.enigma.houseapartement.service.HouseService;
import com.enigma.houseapartement.service.ImageService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final FileStorageService fileStorageService;
    private final ImageService imageService;
    private final HousePriceService housePriceService;

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

        HousePrice housePrice = HousePrice.builder()
                .price(houseRequest.getPrice())
                .createdAt(LocalDateTime.now())
                .house(saveHouse)
                .isActive(true)
                .build();
        housePriceService.create(housePrice);

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
                .price(housePrice.getPrice())
                .images(listImage.stream().map((image ->
                        ImageResponse.builder()
                                .id(image.getId())
                                .fileName(image.getFileName())
                                .time(image.getDateTime().toString())
                                .build()
                )).toList())
                .build();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Page<HouseResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        Specification<House> specification = ((root, query, criteriaBuilder) -> {
            Join<House, HousePrice> housePrices = root.join("housePrices");

            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(housePrices.get("price"), maxPrice));
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        });
        Pageable pageable = PageRequest.of(page, size);
        Page<House> houses = houseRepository.findAll(specification, pageable);
        List<HouseResponse> houseResponses = new ArrayList<>();
        try{
            for (House house : houses.getContent()) {
                Optional<HousePrice> housePrice = house.getHousePrices()
                        .stream().filter(HousePrice::getIsActive).findFirst();

                if (housePrice.isEmpty()) {
                    continue;
                }

                List<ImageResponse> images = imageService.getImageById(house.getId());
                houseResponses.add(HouseResponse.builder()
                        .id(house.getId())
                        .name(house.getName())
                        .city(house.getCity())
                        .state(house.getState())
                        .price(housePrice.get().getPrice())
                        .unit(house.getUnit())
                        .wifi(house.getWifi())
                        .laundry(house.getLaundry())
                        .images(images)
                        .build());

            }
        }catch (NullPointerException e) {
            throw new NullPointerException("House price is null");
        }
        return new PageImpl<>(houseResponses, pageable, houses.getTotalElements());

    }

    @Override
    public HouseResponse getById(String id) {
        House house = houseRepository.getHouseById(id);
        List<ImageResponse> imagesResponse = imageService.getImageById(id);
        if (house == null) {
            throw new NotFoundException("House not already exist");
        }

        return HouseResponse.builder()
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
