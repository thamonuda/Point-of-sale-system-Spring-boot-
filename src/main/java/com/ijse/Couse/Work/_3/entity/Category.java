package com.ijse.Couse.Work._3.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity // Marks this class as a JPA entity
@Getter // Lombok annotation to generate getters
@Setter // Lombok annotation to generate setters
public class Category {

    @Id // Specifies the primary key of the entity
    private Long cateId; // Unique identifier for the category

    @Column(nullable = false) // Specifies that this field cannot be null
    private String name; // Name of the category



    @JsonIgnore // Prevents this field from being serialized into JSON
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true) // Defines a one-to-many relationship with Items
    private List<Items> items; // List of items belonging to this category


}