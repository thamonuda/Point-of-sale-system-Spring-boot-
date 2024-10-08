package com.ijse.Couse.Work._3.entity;

import jakarta.persistence.Id;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing an item in the inventory.
 * 
 * This class represents an item entity, including details such as name,
 * price per unit, category, stock, and orders associated with this item.
 */
@Entity
@Getter // Generates getter methods for all fields
@Setter // Generates setter methods for all fields
public class Items {
    
    @Id // Marks itemId as the primary key
    private Long itemId; // Unique identifier for the item

    @Column(nullable = false) // Marks the column as non-nullable
    private String name; // Name of the item

    @Column(nullable = false) // Marks the column as non-nullable
    private Long priceOneUnit; // Price of one unit of the item

    @ManyToOne // Many items can belong to one category
    @JoinColumn(name = "category_id", nullable = false) // Specifies the foreign key column
    private Category category; // Category to which the item belongs

    @OneToOne(mappedBy = "item") // One-to-one relationship with Stock
    @JoinColumn(name = "stock_id") // Specifies the foreign key column for the stock
    private Stock stock; // Stock information for this item

    @JsonIgnore // Prevents circular reference during JSON serialization
    @ManyToMany(mappedBy = "orderedProducts", cascade = CascadeType.REMOVE) // Defines many-to-many relationship with orders
    private List<Order> orders; // List of orders that include this item
}
