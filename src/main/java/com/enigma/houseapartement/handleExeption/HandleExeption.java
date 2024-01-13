package com.enigma.houseapartement.handleExeption;

import com.enigma.houseapartement.dto.CommondResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;


@RestControllerAdvice
public class HandleExeption {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public CommondResponse handleNotfoundExeption(NotFoundException exception) {
        return CommondResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NullPointerException.class)
    public CommondResponse handleNullPointer(NullPointerException exeption){
        return CommondResponse.builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message(exeption.getMessage())
                .build();
    }
}
