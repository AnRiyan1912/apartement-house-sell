package com.enigma.houseapartement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdminResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private Boolean isActive;
}
