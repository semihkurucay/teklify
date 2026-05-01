package com.semihkurucay.service.impl;

import com.semihkurucay.dto.DtoCategory;
import com.semihkurucay.repository.CategoryRepository;
import com.semihkurucay.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<DtoCategory> dropDownList() {
        return categoryRepository.dropDownList();
    }
}
