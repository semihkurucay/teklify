package com.semihkurucay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DtoCategory extends DtoBaseEntity {

    private String name;

    public DtoCategory(Long id, String name){
        this.setId(id);
        this.name = name;
    }
}
