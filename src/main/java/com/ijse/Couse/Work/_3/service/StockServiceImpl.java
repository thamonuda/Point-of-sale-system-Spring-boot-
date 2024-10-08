package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Stock;
import com.ijse.Couse.Work._3.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStock() {
           return stockRepository.findAll();
    }

    @Override
    public Stock createStock(Stock stock) {
       
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStockByItemId(Long id) {
      return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock updateOrderQty(Long id, Double newQty) {
       Stock exitingOrder = stockRepository.findById(id).orElse(null);

       if(exitingOrder == null){
      return null;
       }else{
        exitingOrder.setQuantity(newQty);
        return stockRepository.save(exitingOrder);
       }

       
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
      
      Stock exitingStock2 = stockRepository.findById(id).orElse(null);

      if(exitingStock2 == null){
        return null;
      }else{
        exitingStock2.setQuantity(stock.getQuantity());
        exitingStock2.setUnit(stock.getUnit());

        return stockRepository.save(exitingStock2);
      }
    }
    
}
