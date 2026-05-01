package com.semihkurucay.repository;

import com.semihkurucay.dto.DtoCategory;
import com.semihkurucay.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.semihkurucay.dto.DtoCategory(c.id, c.name)" +
            "FROM Category c")
    List<DtoCategory> dropDownList();
}
