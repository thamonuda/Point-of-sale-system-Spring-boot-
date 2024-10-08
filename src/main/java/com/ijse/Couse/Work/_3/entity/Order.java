package com.ijse.Couse.Work._3.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private Long getItems;

    private Double totalPrice;


    private LocalDateTime orderDateTime;

    @PrePersist
    protected void onCreate(){
        this.orderDateTime = LocalDateTime.now();
    }


    @ManyToMany
    @JoinTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "items_id")
    )
   
    private List<Items> orderedProducts;

}
