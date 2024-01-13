package com.enigma.houseapartement.service.serviceImpl;

import com.enigma.houseapartement.entity.HousePrice;
import com.enigma.houseapartement.repository.HousePriceRepository;
import com.enigma.houseapartement.service.HousePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HousePriceServiceImpl implements HousePriceService {
    private final HousePriceRepository housePriceRepository;


    @Override
    public HousePrice create(HousePrice housePrice) {
        return housePriceRepository.saveAndFlush(housePrice);
    }

    @Override
    public HousePrice getById(String id) {
        return null;
    }

    @Override
    public void nonActive(HousePrice housePrice) {

    }
}
