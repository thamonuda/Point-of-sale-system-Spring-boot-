package com.ijse.Couse.Work._3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.Couse.Work._3.entity.Category;
import com.ijse.Couse.Work._3.service.CategoryService;

@RestController
@RequestMapping("/categories") // Base URL for the category endpoints
public class CategoryController {

    @Autowired
    private CategoryService categoryServise; // Injecting the CategoryService to handle business logic

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> listCategories = categoryServise.getAllCategories(); // Fetch all categories from the service
        return ResponseEntity.ok(listCategories); // Return the list with an OK status
    }

    // Delete a category by ID
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        Category category = categoryServise.getCategoryById(categoryId); // Find the category by ID
        if (category == null) {
            // If not found, return NOT FOUND status with an appropriate message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category found with ID: " + categoryId);
        } else {
            categoryServise.deleteCategory(categoryId); // Delete the category
            // Return NO CONTENT status indicating successful deletion
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Successfully deleted " + category.getName() + " Category");
        }
    }

    // Get a category by ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryServise.getCategoryById(categoryId); // Fetch category by ID
        if (category == null) {
            // If not found, return NOT FOUND status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            // Return the found category with an OK status
            return ResponseEntity.ok(category);
        }
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        // Validate the category name
        if (category.getName() == null || category.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter a category name to create a category.");
        }

        // Fetch all existing categories to manage IDs efficiently
        List<Category> existingCategories = categoryServise.getAllCategories();
        List<Long> existingIds = existingCategories.stream()
                .map(Category::getCateId) // Map to get the IDs
                .sorted() // Sort the IDs for easier gap finding
                .toList();

        // Manage category IDs to avoid conflicts and gaps
        long newId = 1; // Start assigning IDs from 1
        for (Long id : existingIds) {
            if (id == newId) {
                newId++; // Increment if the ID is already in use
            } else {
                break; // Exit the loop when a gap is found
            }
        }

        // Set the new ID to the category
        category.setCateId(newId);

        // Create the category
        categoryServise.createCategory(category);
        // Return CREATED status with the new category ID
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created category with ID: " + newId);
    }

    @PutMapping("/{categoryId}")
public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
    Category existingCategory = categoryServise.getCategoryById(categoryId);
    if (existingCategory == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Category with ID " + categoryId + " not found.");
    }

    existingCategory.setName(category.getName());  // Update the category fields
    categoryServise.updateCategory(categoryId, existingCategory);  // Save updates
    return ResponseEntity.ok(existingCategory);  // Return updated category
}
    
}