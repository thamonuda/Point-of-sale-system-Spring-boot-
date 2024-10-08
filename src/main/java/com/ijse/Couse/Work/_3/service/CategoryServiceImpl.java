package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Category;
import com.ijse.Couse.Work._3.repository.CategoryRepository;


/**
 * Implementation of the CategoryService interface.
 * 
 * This class contains the business logic for managing Category entities, 
 * including retrieving, creating, and deleting categories.
 */
@Service // Marks this class as a service component in the Spring context
public class CategoryServiceImpl implements CategoryService {

    @Autowired // Automatically injects the CategoryRepository bean
    private CategoryRepository categoryRepository;

  /*   @Autowired // Automatically injects the ItemsRepository bean (if needed in future implementations)
    private ItemsRepository itemsRepository;

    @Autowired // Automatically injects the OrderRepository bean (if needed in future implementations)
    private OrderRepository orderRepository;*/

    @Override
    public List<Category> getAllCategories() {
        // Retrieves all categories from the database
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        // Saves a new category to the database and returns the created entity
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        // Retrieves a category by its ID, returning null if not found
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
       ////////////////////////////
    }

    @Override
    public Category updateCategory(Long categoryId, Category updateCategory) {
       
        Category exitinCategory = categoryRepository.findById(categoryId).orElse(null);

        if(exitinCategory == null){
            return null;
        }else{
            exitinCategory.setCateId(categoryId);
            exitinCategory.setName(updateCategory.getName());
           return categoryRepository.save(exitinCategory);
        }
    }
}