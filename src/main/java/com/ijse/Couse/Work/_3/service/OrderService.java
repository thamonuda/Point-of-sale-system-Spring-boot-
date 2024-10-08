package com.ijse.Couse.Work._3.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ijse.Couse.Work._3.entity.Order;

@Service
public interface OrderService {

    List<Order> getAllOrders();
    Order creatOrder(Order order);

}