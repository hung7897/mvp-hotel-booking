package com.mvphotelbooking.mvphotelbooking.service.impl;

import com.mvphotelbooking.mvphotelbooking.entity.Hotel;
import com.mvphotelbooking.mvphotelbooking.repository.HotelRepository;
import com.mvphotelbooking.mvphotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> searchHotelsByAddress(String street, String city) {
        return hotelRepository.findByAddress(street, city);
    }
}