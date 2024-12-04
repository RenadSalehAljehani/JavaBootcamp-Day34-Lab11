package com.example.bolgsystem_lab11.Controller;

import com.example.bolgsystem_lab11.ApiResponse.ApiResponse;
import com.example.bolgsystem_lab11.Model.Category;
import com.example.bolgsystem_lab11.Repository.CategoryRepository;
import com.example.bolgsystem_lab11.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    // 1. Declare a dependency for CategoryService using Dependency Injection
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
    }

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("New Category Added."));
    }

    // 2.3 Update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Integer categoryId, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.updateCategory(categoryId, category);
        return ResponseEntity.status(200).body(new ApiResponse("Category Updated."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(200).body(new ApiResponse("Category Deleted."));
    }

    // 3. Extra endpoints
    // An endpoint to get category by name
    @GetMapping("/getCategoryByName/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        return ResponseEntity.status(200).body(category);
    }
}