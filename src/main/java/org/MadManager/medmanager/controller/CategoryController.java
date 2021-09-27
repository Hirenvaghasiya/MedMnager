package org.MadManager.medmanager.controller;

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
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping
    public Iterable<Category> getCategories(){
        return categoryDao.findAll();
    }
    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest){

        Category result = categoryDao.save(new Category(addCategoryRequest.getName()));

        URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/category/{name}")
                    .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new APIResponse(true,"Category created successfully"));
    }

}
