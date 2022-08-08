package org.MadManager.medmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.MadManager.medmanager.dao.CategoryDao;
import org.MadManager.medmanager.models.Category;
import org.MadManager.medmanager.payload.APIResponse;
import org.MadManager.medmanager.payload.AddCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by Hiren on 7/15/2017.
 */
@RestController
@RequestMapping("/api/category")
@Tag(name = "Category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping
    @Operation(description = "Get all categories")
    public Iterable<Category> getCategories(){
        return categoryDao.findAll();
    }
    @PostMapping
    @Operation(description = "Add new Category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest){

        Category result = categoryDao.save(new Category(addCategoryRequest.getName()));

        URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/category/{name}")
                    .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new APIResponse(true,"Category created successfully"));
    }

}
