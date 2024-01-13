package com.enigma.houseapartement.repository;

import com.enigma.houseapartement.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query(value = "SELECT * FROM m_image WHERE house_id = :id", nativeQuery = true)
    List<Image> getImageByHouseId(@Param("id") String id);
}
