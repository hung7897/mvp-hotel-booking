package com.mvphotelbooking.mvphotelbooking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDto {
    private int errorCode;
    private String message;
}
