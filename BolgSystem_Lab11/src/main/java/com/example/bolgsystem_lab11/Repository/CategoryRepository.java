package com.example.bolgsystem_lab11.Repository;

import com.example.bolgsystem_lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Using find
    Category findCategoryByCategoryId(Integer categoryId);

    Category findCategoryByName(String name);
}