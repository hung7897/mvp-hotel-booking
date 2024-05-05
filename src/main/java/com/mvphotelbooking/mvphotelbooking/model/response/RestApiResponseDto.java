package com.mvphotelbooking.mvphotelbooking.model.response;

import com.mvphotelbooking.mvphotelbooking.model.HeaderDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestApiResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private HeaderDto header;
    private T body;
}
