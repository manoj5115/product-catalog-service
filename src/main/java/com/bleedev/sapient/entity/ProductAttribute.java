package com.bleedev.sapient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ecom_category_attributes")
public class ProductAttribute {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "attr_name")
    private String name;
    
    @Column(name = "attr_value")
    private String description;
    
    @Column(name = "cat_id")
    private Long categoryId;
}