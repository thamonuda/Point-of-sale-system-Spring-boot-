package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Items;

/**
 * Service interface for managing Items entities.
 * 
 * This interface defines the service methods for handling business logic
 * related to items, including CRUD operations. It acts as an abstraction
 * for item-related operations, which are implemented by a service implementation class.
 */
@Service // Indicates that this is a service interface
public interface ItemService {


    List<Items> getAllItems();

    Items createItems(Items items);

    void deleteItem(Long id);

    Items getItemsById(Long id);

    Items updateItem(Long id, Items Item);
}
