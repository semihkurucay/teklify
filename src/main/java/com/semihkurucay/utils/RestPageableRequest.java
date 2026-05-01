package com.semihkurucay.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPageableRequest {

    private Integer page;
    private Integer size;
    private String column;
    private Boolean asc = true;
}
