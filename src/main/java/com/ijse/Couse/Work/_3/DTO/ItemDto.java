package com.ijse.Couse.Work._3.DTO;


import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Item entity.
 * 
 * This class is used to transfer data between the client and server
 * when creating or updating item entities. It encapsulates the fields
 * required for item creation, ensuring a clear contract for item-related
 * data.
 */
@Getter // Automatically generates getter methods for all fields
@Setter // Automatically generates setter methods for all fields
public class ItemDto {
    
    private String name; // Name of the item

    private Long priceOneUnit; // Price of a single unit of the item

    private Long category_Id; // ID of the category to which the item belongs

    private double quantity; // Quantity of the item available in stock

    private String unit; // Unit of measurement for the quantity (e.g., KG, G, L, ML, UNIT)
}

