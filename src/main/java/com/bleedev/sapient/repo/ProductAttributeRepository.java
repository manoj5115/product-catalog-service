package com.bleedev.sapient.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bleedev.sapient.entity.ProductAttribute;

@Repository
public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {

	Optional<List<ProductAttribute>> findAllByCategoryId(Long id);

}
