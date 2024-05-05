package com.mvphotelbooking.mvphotelbooking.model.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequest {
    @NonNull
    private Long guestId;
    @NonNull
    private Long roomId;
    @NonNull
    private LocalDateTime checkin;
    @NonNull
    private LocalDateTime checkout;
}
