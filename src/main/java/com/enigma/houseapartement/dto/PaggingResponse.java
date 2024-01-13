package com.enigma.houseapartement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaggingResponse {
    private Integer currentPage;
    private Integer totalPage;
    private Integer size;
}
