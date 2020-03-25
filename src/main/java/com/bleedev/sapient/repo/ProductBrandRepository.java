package com.bleedev.sapient.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.sapient.entity.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

	Optional<List<ProductBrand>> findByNameContainingIgnoreCase(String brand);

}
