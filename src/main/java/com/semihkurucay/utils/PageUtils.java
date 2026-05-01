package com.semihkurucay.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@UtilityClass
public class PageUtils {

    private Boolean isEmpty(String name){
        return name == null || name.trim().isEmpty();
    }

    public Pageable toPageable(RestPageableRequest request) {
        if(isEmpty(request.getColumn())){
            return PageRequest.of(request.getPage(), request.getSize());
        }

        Sort sort = Sort.by(request.getAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, request.getColumn());
        return PageRequest.of(request.getPage(), request.getSize(), sort);
    }

    public <T> RestPageableEntity<T> toRestPageableEntity(Page<?> page, List<T> content){
        RestPageableEntity<T> entity = new RestPageableEntity<>();

        entity.setContent(content);
        entity.setNumber(page.getNumber());
        entity.setSize(page.getSize());
        entity.setTotalElements(page.getTotalElements());
        entity.setTotalPages(page.getTotalPages());

        return entity;
    }
}
