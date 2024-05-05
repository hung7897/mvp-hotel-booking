package com.mvphotelbooking.mvphotelbooking.controller;

import com.mvphotelbooking.mvphotelbooking.exception.RoomException;
import com.mvphotelbooking.mvphotelbooking.model.ErrorResponseDto;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingDetailRequest;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingRequest;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingDetailsResponse;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingResponse;
import com.mvphotelbooking.mvphotelbooking.service.BookingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hotel")
public class HotelBookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> bookRoom(@Validated @RequestBody BookingRequest bookingRequest) {
        try {
            if (bookingRequest.getCheckin().isAfter(bookingRequest.getCheckout())) {
                return ResponseEntity.badRequest()
                        .body(ErrorResponseDto.builder()
                                .errorCode(HttpStatus.BAD_REQUEST.value())
                                .message("checkout time must be after checkin time.")
                                .build());
            }

            BookingResponse reservation = bookingService.bookRoom(bookingRequest);
            return ResponseEntity.ok(reservation);
        } catch (NotFoundException | RoomException e) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponseDto.builder()
                            .errorCode(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/booking")
    public ResponseEntity<?> GetBookRoomDetails(@RequestParam(name = "guestId") Long guestId,
                                                @RequestParam(name = "roomId", required = false) Long roomId) {
        try {
            BookingDetailRequest bookingRequest = BookingDetailRequest.builder().guestId(guestId).roomId(roomId).build();
            BookingDetailsResponse reservation = bookingService.findBookingDetails(bookingRequest);
            return ResponseEntity.ok(reservation);
        } catch (NotFoundException | RoomException e) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponseDto.builder()
                            .errorCode(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .build());
        }


    }
}
