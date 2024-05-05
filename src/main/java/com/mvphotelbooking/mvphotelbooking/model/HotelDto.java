package com.mvphotelbooking.mvphotelbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private String name;

    private AddressDto address;

    @JsonIgnore
    private List<RoomDto> rooms;

    private String phone;

    private String email;
}
