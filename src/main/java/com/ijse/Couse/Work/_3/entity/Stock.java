package com.ijse.Couse.Work._3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private double quantity;

    private String unit;

    public enum Unit {
        KG,ML, UNIT ;// add more if needed
    }

    public Stock() {}

    public Stock(double quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit.name();
    }

    // Method to return a formatted quantity with unit
    public String getFormattedQuantity() {
        return this.quantity + " " + this.unit;
    }

    // Method to set quantity with validation
    public void setQuantity(double quantity, Unit unit) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative.");
        }
        this.quantity = quantity;
        this.unit = unit.name();
    }

@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "item_id")
private Items item;

}
