package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.utils.PageUtils;
import com.semihkurucay.utils.RestPageableEntity;
import com.semihkurucay.utils.RestPageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

class RestBaseController {

    public <T>RootEntity<T> ok(T data) {
        return RootEntity.ok(data);
    }

    public Pageable toPageableEntity(RestPageableRequest request){
        return PageUtils.toPageable(request);
    }

    public <T> RestPageableEntity<T> toPageableEntity(Page<?> page, List<T> content) {
        return PageUtils.toRestPageableEntity(page, content);
    }
}
