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
@Table(name = "ecom_stocks")
public class ProductStocks {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	@Column(name = "seller_id")
    private Integer sellerId;
 
	@Column(name = "product_id")
    private Integer productId;
	
	@Column(name = "quantity")
    private Integer quantity;
    
    
}