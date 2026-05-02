package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DtoUser extends DtoBaseDateEntity {

    private String tc;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private DtoAddress address;
    private DtoContact contact;
    private String username;
}
