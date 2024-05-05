package com.mvphotelbooking.mvphotelbooking.service;

import com.mvphotelbooking.mvphotelbooking.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> searchHotelsByAddress(String street, String city);
}
