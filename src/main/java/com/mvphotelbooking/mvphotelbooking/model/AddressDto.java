package com.mvphotelbooking.mvphotelbooking.model;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;
}
