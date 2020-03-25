package com.bleedev.sapient.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.sapient.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findBySku(long sku);

	List<Product> findAllByBrandIdIn(List<Long> brands);

}
