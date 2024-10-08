package com.ijse.Couse.Work._3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Order;
import com.ijse.Couse.Work._3.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
      return orderRepository.findAll();
      
    }

    @Override
    public Order creatOrder(Order order) {
      
        return orderRepository.save(order);
    }


    
}
