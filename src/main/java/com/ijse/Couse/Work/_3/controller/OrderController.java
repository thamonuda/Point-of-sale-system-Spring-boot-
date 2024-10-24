package com.ijse.Couse.Work._3.controller;

import com.ijse.Couse.Work._3.DTO.OrderDto;
import com.ijse.Couse.Work._3.entity.Items;
import com.ijse.Couse.Work._3.entity.Order;
import com.ijse.Couse.Work._3.service.ItemService;
import com.ijse.Couse.Work._3.service.OrderService;
import com.ijse.Couse.Work._3.service.StockService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to manage Orders.
 * It handles HTTP requests related to creating and retrieving orders.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StockService stockService;

 
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderdto) {

        // Initialize a new order with default values
        Order order = new Order();
        order.setTotalPrice(0.0);

        // Retrieve item IDs and quantities from the DTO
        List<Double> qty = orderdto.getQty();
        List<Long> itemIds = orderdto.getItemIds();
        List<Items> itemsList = new ArrayList<>();
        Long getItems = 0L;

        // Loop through each item ID to validate and add to the order
        for (int i = 0; i < itemIds.size(); i++) {
            Double newQty = 0.0;

            // Get item ID and the corresponding quantity
            Long itemId = itemIds.get(i);
            Double quantity = qty.get(i);

            // Retrieve the item from the inventory
            Items item = itemService.getItemsById(itemId);

            // Check if the item is out of stock or has insufficient stock
            if (item.getStock().getQuantity() == 0) {
                continue;

            } else if (item.getStock().getQuantity() < quantity) {
                continue;
            } else {
                // Deduct quantity and add item to the order
                getItems++;
                newQty = item.getStock().getQuantity() - quantity;

                // Update the total price of the order
                order.setTotalPrice(order.getTotalPrice() + (item.getPriceOneUnit() * quantity));

                // Update stock quantity for the item
                stockService.updateOrderQty(itemId, newQty);

                // Add item to the order's product list
                itemsList.add(item);
            }
        }

        // Set the list of ordered items and the count of items in the order
        order.setGetItems(getItems);
        order.setOrderedProducts(itemsList);

        // Save the order and return the response
        Order savedOrder = orderService.creatOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

 
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
