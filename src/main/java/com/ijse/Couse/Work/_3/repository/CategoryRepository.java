package com.ijse.Couse.Work._3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.Couse.Work._3.entity.Category;

/**
 * Repository interface for the Category entity.
 * 
 * This interface extends JpaRepository, which provides various methods for
 * interacting with the database such as save, delete, and find operations.
 * The generic parameters specify that this repository manages the
 * Category entity with a primary key of type Long.
 */
@Repository // Indicates that this interface is a Spring Data repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // No additional methods are defined here, but you can add custom query methods
    // if needed.
}