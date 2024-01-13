package com.enigma.houseapartement.dto;

import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NegativeOrZero
@Builder(toBuilder = true)
public class PaggingCommondResponse <T>{
    private Integer statusCode;
    private String message;
    private T data;
    private PaggingResponse paggingResponse;
}
