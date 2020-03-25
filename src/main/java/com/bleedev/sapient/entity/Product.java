package com.bleedev.sapient.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Data
@Table(name = "ecom_product")
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sku;
 
    @NotNull(message = "Product name is mandatory.")
    @Basic(optional = false)
    private String name;
 
    @Column(name = "price")
    private Integer price;
    
    @Column(name = "brand_id")
    private Long brandId;
    
    @Column(name = "seller_id")
    private Long sellerId;
    
    @Column(name = "cat_id")
    private Long categoryId;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private ProductSeller sellerDetails;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private ProductBrand brandDetails;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private ProductCategory categoryDetails;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private List<ProductAttribute> attributes;
}
