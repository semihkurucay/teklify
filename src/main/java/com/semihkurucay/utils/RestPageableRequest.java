package com.semihkurucay.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPageableRequest {

    private Integer page = 0;
    private Integer size = 10;
    private String column;
    private Boolean asc = false;
}
