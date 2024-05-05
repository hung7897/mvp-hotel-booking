package com.mvphotelbooking.mvphotelbooking.model.response;

import com.mvphotelbooking.mvphotelbooking.model.HotelDto;
import com.mvphotelbooking.mvphotelbooking.model.RoomDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private HotelDto hotel;
    private RoomDto room;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
}
