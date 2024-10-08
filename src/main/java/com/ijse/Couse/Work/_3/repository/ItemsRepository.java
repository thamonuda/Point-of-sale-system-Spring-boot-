package com.ijse.Couse.Work._3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.Couse.Work._3.entity.Items;



/**
 * Repository interface for managing Items entities.
 * 
 * This interface extends JpaRepository, providing basic CRUD operations
 * for the Items entity. Custom queries can be added here if needed in the future.
 */
@Repository // Marks this interface as a repository for persistence operations
public interface ItemsRepository extends JpaRepository<Items, Long> {
    
    // The JpaRepository interface provides built-in methods for basic CRUD operations:
    // save(), findById(), findAll(), deleteById(), etc.

    // Custom queries can be added here if more complex database operations are required.
}
