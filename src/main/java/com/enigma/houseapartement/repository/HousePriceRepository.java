package com.enigma.houseapartement.repository;

import com.enigma.houseapartement.entity.HousePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousePriceRepository extends JpaRepository<HousePrice, String> {
}
