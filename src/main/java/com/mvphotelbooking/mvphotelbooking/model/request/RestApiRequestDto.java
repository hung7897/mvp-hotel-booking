package com.mvphotelbooking.mvphotelbooking.model.request;

import com.mvphotelbooking.mvphotelbooking.model.HeaderDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestApiRequestDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private HeaderDto header;
    private T body;
}
