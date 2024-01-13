package com.enigma.houseapartement.repository;

import com.enigma.houseapartement.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HouseRepository extends JpaRepository<House, String> {
    @Query(value = "INSERT INTO m_house (id, name, city, state, photo, unit, wifi, laundry, is_active, created_at, updated_at)" +
            " VALUES (gen_random_uuid(), :name, :city, :state, :photo, :unit, :wifi, :laundry, :isActive, :createdAt, :updatedAt)", nativeQuery = true)
    House create(@Param("name") String name, @Param("city") String city, @Param("state") String state, @Param("photo") String photo, @Param("unit") Integer unit, @Param("wifi") Boolean wifi, @Param("laundry") Boolean laundry, @Param("isActive") Boolean isActive, @Param("createdAt") LocalDateTime createdAt, @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "SELECT * FROM m_house WHERE id = :id", nativeQuery = true)
    House getHouseById(@Param("id") String id);
}
