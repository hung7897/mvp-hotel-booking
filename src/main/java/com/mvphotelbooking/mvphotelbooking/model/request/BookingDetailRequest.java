package com.mvphotelbooking.mvphotelbooking.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookingDetailRequest {
    @NonNull
    private Long guestId;
    private Long roomId;
}
