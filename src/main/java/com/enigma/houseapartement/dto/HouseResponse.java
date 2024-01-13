package com.enigma.houseapartement.dto;

import com.enigma.houseapartement.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HouseResponse {
    private String id;
    private String name;
    private String city;
    private String state;
    private Integer unit;
    private Boolean wifi;
    private Boolean laundry;
    private List<ImageResponse> images;
}
