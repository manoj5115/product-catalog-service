package com.bleedev.sapient.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.sapient.entity.ProductSeller;

@Repository
public interface ProductSellerRepository extends JpaRepository<ProductSeller, Long> {


}
