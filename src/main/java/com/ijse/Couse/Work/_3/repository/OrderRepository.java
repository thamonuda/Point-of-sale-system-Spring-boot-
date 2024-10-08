package com.ijse.Couse.Work._3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.ijse.Couse.Work._3.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

    
}
