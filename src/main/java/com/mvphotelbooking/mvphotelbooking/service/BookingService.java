package com.mvphotelbooking.mvphotelbooking.service;

import com.mvphotelbooking.mvphotelbooking.model.request.BookingDetailRequest;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingRequest;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingDetailsResponse;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingResponse;
import javassist.NotFoundException;

public interface BookingService {
    BookingResponse bookRoom(BookingRequest bookingRequest) throws NotFoundException;
    BookingDetailsResponse findBookingDetails(BookingDetailRequest bookingRequest) throws NotFoundException;
}
