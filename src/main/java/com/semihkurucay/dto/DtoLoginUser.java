package com.semihkurucay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoLoginUser {

    private Long id;
    private String fullname;
    private BigDecimal balance;
}
