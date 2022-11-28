package com.nocountry.ecommerce.ports.input.rs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nocountry.ecommerce.domain.model.Category;
import com.nocountry.ecommerce.ports.input.rs.request.CategoryRequest;
import com.nocountry.ecommerce.ports.input.rs.response.CategoryDetails;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category CategoryRequestToCategory(CategoryRequest request);

    CategoryDetails CategoryToCategoryDetails(Category category);

    List<CategoryDetails> CategoryListToCategoryDetailList(List<Category> list);
}
