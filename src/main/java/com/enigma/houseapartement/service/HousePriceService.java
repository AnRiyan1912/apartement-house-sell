package com.enigma.houseapartement.service;

import com.enigma.houseapartement.entity.HousePrice;

public interface HousePriceService {
    HousePrice create(HousePrice housePrice);
    HousePrice getById(String id);
    void nonActive(HousePrice housePrice);
}
