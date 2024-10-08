package com.ijse.Couse.Work._3.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDto {
    
    
    private Long stockId;
    private String itemName;
    private double quantity;
    private String unit;
    private String formattedQuantity;
   

    public StockResponseDto(Long stockId, String itemName, double quantity, String unit, String formattedQuantity) {
        this.stockId = stockId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.formattedQuantity = formattedQuantity;
       
    }
}
