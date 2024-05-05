package com.mvphotelbooking.mvphotelbooking.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsResponse {
    List<BookingResponse> bookingResponseList;
}
