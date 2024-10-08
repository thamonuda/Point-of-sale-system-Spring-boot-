package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Items;
import com.ijse.Couse.Work._3.repository.ItemsRepository;
import com.ijse.Couse.Work._3.repository.StockRepository;

/**
 * Implementation of the ItemService interface for managing Items entities.
 * 
 * This class contains the business logic for handling item-related operations,
 * including creating, retrieving, and deleting items. It uses repository classes
 * to interact with the database.
 */
@Service // Marks this class as a service bean to be managed by the Spring container
public class ItemServiceImpl implements ItemService {

    @Autowired // Injects the ItemsRepository bean for data persistence
    private ItemsRepository itemsRepository;

    @Autowired // Injects the StockRepository bean for stock-related operations
    private StockRepository stockRepository;




    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }


    @Override
    public Items createItems(Items items) {
        return itemsRepository.save(items);
    }


    @Override
    public void deleteItem(Long id) {
        // Delete associated order and stock entities before deleting the item itself
        stockRepository.deleteById(id);
        itemsRepository.deleteById(id);
    }


    @Override
    public Items getItemsById(Long id) {
        return itemsRepository.findById(id).orElse(null);
    }



    @Override
    public Items updateItem(Long id, Items item) {
              // Retrieve the existing item from the repository using its ID
              Items existingItem = itemsRepository.findById(id).orElse(null);
    
              // Check if the item is found
              if (existingItem == null) {
                  return null;  // Return null or throw an exception if not found
              }
          
              // Update the fields of the existing item with the new values
              existingItem.setName(item.getName());
              existingItem.setPriceOneUnit(item.getPriceOneUnit());
              existingItem.setCategory(item.getCategory());
          
              // If Stock information needs to be updated, ensure Stock entity is updated
              if (item.getStock() != null) {
                  existingItem.getStock().setQuantity(item.getStock().getQuantity());
                  // Other stock fields can be updated here as needed
              }
          
              // Save the updated item back to the repository
              Items updatedItem = itemsRepository.save(existingItem);
          
              // Return the updated item
              return updatedItem;
    }
}
