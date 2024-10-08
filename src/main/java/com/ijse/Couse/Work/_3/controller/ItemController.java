package com.ijse.Couse.Work._3.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.Couse.Work._3.DTO.ItemDto;
import com.ijse.Couse.Work._3.entity.Category;
import com.ijse.Couse.Work._3.entity.Items;
import com.ijse.Couse.Work._3.entity.Stock;
import com.ijse.Couse.Work._3.service.CategoryService;
import com.ijse.Couse.Work._3.service.ItemService;
import com.ijse.Couse.Work._3.service.StockService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * Controller for managing items in the inventory.
 * 
 * This class handles HTTP requests related to items, including
 * retrieving, creating, and deleting item entities. It uses
 * services to perform operations on items and their associated
 * categories and stocks.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    
    @Autowired // Automatically injects the ItemService bean
    private ItemService itemService;

    @Autowired // Automatically injects the CategoryService bean
    private CategoryService categoryService;

    @Autowired // Automatically injects the StockService bean
    private StockService stockService;


    @GetMapping
    public ResponseEntity<List<Items>> getAllItems() {
        List<Items> listItems = itemService.getAllItems();
        return ResponseEntity.status(200).body(listItems);
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody ItemDto itemDto) {
        // Validate item name
        if (itemDto.getName() == null || itemDto.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Item name cannot be empty. Please provide a valid item name.");
        }

        // Get all existing items and their IDs
        List<Items> existingItems = itemService.getAllItems();
        List<Long> existingIds = existingItems.stream()
                                              .map(Items::getItemId)
                                              .sorted()
                                              .toList();

        // Find the first available ID in the sequence
        long newId = 1; // Start with 1
        for (Long id : existingIds) {
            if (id == newId) {
                newId++;
            } else {
                break; // Gap found
            }
        }

        // Create new Items and Stock
        Items newItems = new Items();
        Stock newStock = new Stock();

        // Set ID for new item and stock
        newItems.setItemId(newId);
        newStock.setStockId(newId);

        // Set quantity and unit for stock
        try {
            Stock.Unit unitEnum = Stock.Unit.valueOf(itemDto.getUnit().toUpperCase());
            newStock.setQuantity(itemDto.getQuantity(), unitEnum);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid unit type. Please provide a valid unit (KG, G, L, ML, UNIT).");
        }

        // Set other details for Items
        newItems.setName(itemDto.getName());
        newItems.setPriceOneUnit(itemDto.getPriceOneUnit());

        // Assign category to the item
        Category category = categoryService.getCategoryById(itemDto.getCategory_Id());
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Category with ID " + itemDto.getCategory_Id() + " not found.");
        }
        newItems.setCategory(category);

        // Link the Stock with the Items
        newStock.setItem(newItems);

        // Save the new Items and Stock
        itemService.createItems(newItems);
        stockService.createStock(newStock);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Successfully created item with ID: " + newId);
    }


     //* Deletes an item by its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        Items items = itemService.getItemsById(id);
        if (items == null) {
            return ResponseEntity.status(404).body("No Item found with ID: " + id);
        } else {
            itemService.deleteItem(id);
            return ResponseEntity.ok("Item has been marked as deleted.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Items items = itemService.getItemsById(id);
        if (items == null) {
            return ResponseEntity.status(404).body("No Item found with ID: " + id);
        } else {
            return ResponseEntity.status(200).body(items);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        // Retrieve the existing item and stock
        Items exitingItem = itemService.getItemsById(id);
        Stock exitingStock = stockService.getStockByItemId(id);
    
        // Check if the item exists
        if (exitingItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Item with ID " + id + " not found.");
        }
    
        // Update the item fields
        exitingItem.setName(itemDto.getName());
        exitingItem.setPriceOneUnit(itemDto.getPriceOneUnit());
    
        // Retrieve and set the category
        Category category = categoryService.getCategoryById(itemDto.getCategory_Id());
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Category with ID " + itemDto.getCategory_Id() + " not found.");
        }
        exitingItem.setCategory(category);
    
        // Update stock information
        if (exitingStock != null) {
            exitingStock.setQuantity(itemDto.getQuantity());
            exitingStock.setUnit(itemDto.getUnit());
            stockService.updateStock(id, exitingStock);  // Update stock
        }
    
        // Update the item
        itemService.updateItem(id, exitingItem);
    
        return ResponseEntity.ok(exitingItem);
    }
}