package com.ijse.Couse.Work._3.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Stock;



@Service
public interface StockService {
    

    List<Stock> getAllStock();

    Stock createStock(Stock stock);
    Stock getStockByItemId(Long id);
   // void deleteItem(Long id);
   Stock updateOrderQty(Long id,Double newQty);

    Stock updateStock(Long id, Stock stock);
  
}
