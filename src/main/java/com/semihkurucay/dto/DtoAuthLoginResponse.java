package com.semihkurucay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuthLoginResponse extends DtoAuthResponse {

    private DtoLoginUser user;
}
