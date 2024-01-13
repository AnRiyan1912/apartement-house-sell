package com.enigma.houseapartement.service;

import com.enigma.houseapartement.dto.HouseRequest;
import com.enigma.houseapartement.dto.HouseResponse;

import java.util.List;

public interface HouseService {
    HouseResponse create(HouseRequest houseRequest);
    List<HouseResponse> getAll();
    HouseResponse getById(String id);
    HouseResponse remove(String id);
}
