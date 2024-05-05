package com.mvphotelbooking.mvphotelbooking.controller;

import com.mvphotelbooking.mvphotelbooking.entity.Hotel;
import com.mvphotelbooking.mvphotelbooking.model.ErrorResponseDto;
import com.mvphotelbooking.mvphotelbooking.model.response.RestApiResponseDto;
import com.mvphotelbooking.mvphotelbooking.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search-by-address")
    public ResponseEntity<?> searchHotelsByAddress(
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "city", required = false) String city) {
        log.info("searchHotelsByAddress {}, {}", street, city);
        if (!StringUtils.hasText(street) && !StringUtils.hasText(city)) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponseDto.builder()
                            .errorCode(HttpStatus.BAD_REQUEST.value())
                            .message("Either street or city must be populated.")
                            .build());
        }

        List<Hotel> hotels = hotelService.searchHotelsByAddress(street, city);

        if (hotels.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponseDto.builder()
                            .errorCode(HttpStatus.BAD_REQUEST.value())
                            .message("No hotels found in the provided location.")
                            .build());
        }

        return ResponseEntity.ok(RestApiResponseDto.builder().body(hotels).build());
    }

}
