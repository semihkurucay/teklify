package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DtoUserUpdate {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private DtoAddressIU address;
    private DtoContactIU contact;
    private String password;
}
