package com.enigma.houseapartement.service;

import com.enigma.houseapartement.dto.HouseRequest;
import com.enigma.houseapartement.dto.HouseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {
    HouseResponse create(HouseRequest houseRequest);
    Page<HouseResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
    HouseResponse getById(String id);
    HouseResponse remove(String id);
}
