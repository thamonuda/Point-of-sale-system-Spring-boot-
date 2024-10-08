package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Category;

/**
 * Service interface for managing Category entities.
 * 
 * This interface defines the methods for performing operations 
 * related to categories, including retrieving, creating, and deleting 
 * category entities. Implementations of this interface will 
 * contain the business logic for these operations.
 */
@Service // Indicates that this interface is a service component in the Spring context
public interface CategoryService {

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category getCategoryById(Long id);

    void deleteCategory(Long id);

    Category updateCategory(Long categoryId, Category updateCategory);
}