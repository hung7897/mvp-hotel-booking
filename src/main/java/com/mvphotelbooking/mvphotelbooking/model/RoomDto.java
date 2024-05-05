package com.mvphotelbooking.mvphotelbooking.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private String number;

    private String type;

    private BigDecimal price;

}
