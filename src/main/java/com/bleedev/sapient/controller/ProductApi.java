package com.bleedev.sapient.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bleedev.sapient.entity.Product;
import com.bleedev.sapient.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/productApi/v1")
@Slf4j
public class ProductApi {

	private final ProductService productService;

	public ProductApi(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/products/{sku}")
	public Product getProductBySku(@PathVariable("sku") String sku) {
		return productService.getProductBySku(sku);
	}

	@GetMapping("/products/brand/{brand}")
	public ResponseEntity<?> getProductsByGivenBrand(@PathVariable("brand") String brand) {
		List<Product> products = productService.getProductsByBrand(brand);
		if(CollectionUtils.isEmpty(products)) {
			log.info("No Product is available in the Catlaog/Inventory matching the Brand: {}", brand);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product is available in the Catlaog/Inventory matching the Brand.");
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products/brand")
	public Map<String, List<Product>> getAllProductsGroupByBrand() {
		return productService.getAllProductsGroupByBrand();
	}
	
	@GetMapping("/products/stock/{seller}")
	public Map<Integer, Integer> getProductsInStockBySeller(@PathVariable("seller") String sellerId) {
		return productService.getProductsInStockBySeller(sellerId);
	}
}