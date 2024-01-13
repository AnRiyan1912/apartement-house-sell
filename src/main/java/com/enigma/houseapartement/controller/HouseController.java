package com.enigma.houseapartement.controller;

import com.enigma.houseapartement.constants.PathRoute;
import com.enigma.houseapartement.dto.CommondResponse;
import com.enigma.houseapartement.dto.HouseRequest;
import com.enigma.houseapartement.dto.HouseResponse;
import com.enigma.houseapartement.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping(PathRoute.PATCH_HOUSE)
public class HouseController {
    private final HouseService houseService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CommondResponse> createHouse(@RequestParam("name") String name, @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("unit") Integer unit, @RequestParam("wifi")Boolean wifi, @RequestParam("laundry") Boolean laundry, @RequestParam("file") MultipartFile[] files){
        HouseRequest houseRequest = HouseRequest.builder()
                .name(name)
                .city(city)
                .state(state)
                .unit(unit)
                .wifi(wifi)
                .laundry(laundry)
                .file(files)
                .build();

        HouseResponse houseResponse = houseService.create(houseRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommondResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Success create house")
                        .data(houseResponse)
                        .build()
        );
    }

    @GetMapping(PathRoute.PATH_ID)
    public ResponseEntity<CommondResponse> getHouseById(@PathVariable String id) {
        HouseResponse houseResponse = houseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommondResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success get house")
                        .data(houseResponse)
                        .build()
        );
    }
}
