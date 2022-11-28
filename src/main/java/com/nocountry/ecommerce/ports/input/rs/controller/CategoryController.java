package com.nocountry.ecommerce.ports.input.rs.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;

import com.nocountry.ecommerce.domain.usecase.CategoryService;
import com.nocountry.ecommerce.ports.input.rs.mapper.CategoryMapper;
import com.nocountry.ecommerce.ports.input.rs.request.CategoryRequest;
import com.nocountry.ecommerce.ports.input.rs.response.CategoryDetails;

import lombok.RequiredArgsConstructor;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(CATEGORY_URI)
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper mapper;


    //====================Display all====================//

    @GetMapping
    public ResponseEntity<List<CategoryDetails>> getAllCategories() {
        return ResponseEntity.ok(mapper.CategoryListToCategoryDetailList(categoryService.findAll()));
    }

    //====================Get by id====================//

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDetails> getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.CategoryToCategoryDetails(categoryService.getByIdIfExists(id)));
    }


    //====================Create====================//

    @PreAuthorize(ADMIN)
    @PostMapping(path = "/create")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryCreateRequest) {
        long id = categoryService.save(mapper.CategoryRequestToCategory(categoryCreateRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    //====================Update====================//

    @PreAuthorize(ADMIN)
    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@Valid @NotNull @PathVariable("id") Long id,
                           @RequestBody CategoryRequest request) {
        categoryService.update(id, mapper.CategoryRequestToCategory(request));
    }



    //====================Deletes====================//

    @PreAuthorize(ADMIN)
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@Valid @NotNull @PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
