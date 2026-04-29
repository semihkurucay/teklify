package com.semihkurucay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthResponse {

    private String accessToken;
    private String refreshToken;
}
