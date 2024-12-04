package com.example.bolgsystem_lab11.Service;

import com.example.bolgsystem_lab11.ApiResponse.ApiException;
import com.example.bolgsystem_lab11.Model.Category;
import com.example.bolgsystem_lab11.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    // 1. Declare a dependency for UserRepository using Dependency Injection
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 2. CRUD
    // 2.1 Get
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 2.2 Post
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // 2.3 Update
    public void updateCategory(Integer categoryId, Category category) {
        Category oldCategory = categoryRepository.findCategoryByCategoryId(categoryId);
        if (oldCategory == null) {
            throw new ApiException("Category Not Found.");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    // 2.4 Delete
    public void deleteCategory(Integer categoryId) {
        Category oldCategory = categoryRepository.findCategoryByCategoryId(categoryId);
        if (oldCategory == null) {
            throw new ApiException("Category Not Found.");
        }
        categoryRepository.delete(oldCategory);
    }

    // 3. Extra endpoints
    // An endpoint to get category by name
    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        if (categoryRepository.findCategoryByName(name) == null) {
            throw new ApiException("Category Not Found.");
        }
        return category;
    }
}