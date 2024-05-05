package com.mvphotelbooking.mvphotelbooking.exception;

import com.mvphotelbooking.mvphotelbooking.model.ErrorResponseDto;
import com.mvphotelbooking.mvphotelbooking.model.HeaderDto;
import com.mvphotelbooking.mvphotelbooking.model.response.RestApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<RestApiResponseDto> unexpectedExceptionHandle(Exception ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("unexpected error happened.").build();
        RestApiResponseDto<?> responseDtoRestApiResponseDto = RestApiResponseDto.builder().body(errorResponseDto).header(new HeaderDto()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseDtoRestApiResponseDto);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<RestApiResponseDto> handleHotelNotFoundException(HotelNotFoundException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().errorCode(HttpStatus.NOT_FOUND.value()).message("Hotel can not be found").build();
        RestApiResponseDto<?> responseDtoRestApiResponseDto = RestApiResponseDto.builder().body(errorResponseDto).header(new HeaderDto()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseDtoRestApiResponseDto);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<RestApiResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().errorCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        RestApiResponseDto<?> responseDtoRestApiResponseDto = RestApiResponseDto.builder().body(errorResponseDto).header(new HeaderDto()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseDtoRestApiResponseDto);
    }
}
