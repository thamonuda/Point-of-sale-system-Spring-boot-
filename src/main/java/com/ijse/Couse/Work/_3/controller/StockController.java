package com.ijse.Couse.Work._3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ijse.Couse.Work._3.DTO.StockResponseDto;

import com.ijse.Couse.Work._3.entity.Stock;

import com.ijse.Couse.Work._3.service.StockService;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public ResponseEntity<List<StockResponseDto>> getAllStock() {

        // Fetch all stock entities
        List<Stock> listStock = stockService.getAllStock();

        // Map the list of Stock entities to a list of StockResponseDto objects
        List<StockResponseDto> stockResponseDtoList = listStock.stream()
                .map(stock -> new StockResponseDto(
                        stock.getStockId(),
                        stock.getItem() != null ? stock.getItem().getName() : null, // Get item name if item is not null
                        stock.getQuantity(),
                        stock.getUnit(),
                        stock.getFormattedQuantity()))
                .toList();

        // Return the response with the list of DTOs
        return ResponseEntity.status(200).body(stockResponseDtoList);
    }
}
