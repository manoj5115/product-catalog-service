package com.bleedev.sapient.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.sapient.entity.ProductStocks;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStocks, Long> {

	List<ProductStocks> findAllBySellerId(int sellerId);

}
