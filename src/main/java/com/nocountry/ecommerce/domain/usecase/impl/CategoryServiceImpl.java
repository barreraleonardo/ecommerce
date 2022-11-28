package com.nocountry.ecommerce.domain.usecase.impl;

import com.nocountry.ecommerce.common.exception.error.ExistingNameException;
import com.nocountry.ecommerce.common.exception.error.ResourceNotFoundException;
import com.nocountry.ecommerce.domain.model.Category;
import com.nocountry.ecommerce.domain.repository.CategoryRepository;
import com.nocountry.ecommerce.domain.usecase.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final String NAME = "Category";



    //===================Find===================//

    @Transactional(readOnly = true)
    public Category getByIdIfExists(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NAME,id));
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    //===================Update===================//

    @Transactional
    public Long save(Category request) {
        existsName(request.getName());
        return categoryRepository.save(request).getId();
    }

    @Transactional
    public void update(Long id, Category request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NAME,id));

        existsName(request.getName());
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    //===================Delete===================//

    @Transactional
    public void deleteById(Long id) {
        Category category = getByIdIfExists(id);
        categoryRepository.delete(category);
    }

    //===================Util===================//

    private void existsName(String name) {
        if (categoryRepository.findByName(name).isPresent()) throw new ExistingNameException(name);
    }


}
