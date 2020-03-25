package com.bleedev.sapient.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import static java.util.stream.Collectors.*;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bleedev.sapient.entity.Product;
import com.bleedev.sapient.entity.ProductAttribute;
import com.bleedev.sapient.entity.ProductBrand;
import com.bleedev.sapient.entity.ProductCategory;
import com.bleedev.sapient.entity.ProductSeller;
import com.bleedev.sapient.entity.ProductStocks;
import com.bleedev.sapient.repo.ProductAttributeRepository;
import com.bleedev.sapient.repo.ProductBrandRepository;
import com.bleedev.sapient.repo.ProductCategoryRepository;
import com.bleedev.sapient.repo.ProductRepository;
import com.bleedev.sapient.repo.ProductSellerRepository;
import com.bleedev.sapient.repo.ProductStockRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	private final ProductSellerRepository productSellerRepository;

	private final ProductBrandRepository productBrandRepository;

	private final ProductCategoryRepository productCategoryRepository;

	private final ProductAttributeRepository productAttributeRepository;

	private final ProductStockRepository productStocksRepository;

	public ProductService(final ProductRepository productRepository,
			final ProductSellerRepository productSellerRepository, final ProductBrandRepository productBrandRepository,
			final ProductCategoryRepository productCategoryRepository,
			final ProductAttributeRepository productAttributeRepository,
			final ProductStockRepository productStocksRepository) {
		this.productStocksRepository = productStocksRepository;
		this.productCategoryRepository = productCategoryRepository;
		this.productAttributeRepository = productAttributeRepository;
		this.productBrandRepository = productBrandRepository;
		this.productSellerRepository = productSellerRepository;
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		enrichMultipleProducts(products);
		return products;
	}

	public Product getProductBySku(String sku) {
		Product product = productRepository.findBySku(Long.parseLong(sku));
		enrichSingleProduct(product);
		return product;
	}

	private void enrichSingleProduct(Product product) {
		ProductSeller sellerDetails = productSellerRepository.findById(product.getSellerId()).orElse(null);
		product.setSellerDetails(sellerDetails);

		ProductBrand brandDetails = productBrandRepository.findById(product.getBrandId()).orElse(null);
		product.setBrandDetails(brandDetails);

		ProductCategory catDetails = productCategoryRepository.findById(product.getCategoryId()).orElse(null);
		product.setCategoryDetails(catDetails);

		if (Objects.nonNull(catDetails)) {
			List<ProductAttribute> attributes = productAttributeRepository
					.findAllByCategoryId(product.getCategoryDetails().getId()).orElse(null);
			product.setAttributes(attributes);
		}

	}

	private void enrichMultipleProducts(List<Product> products) {
		for (Product product : products) {
			enrichSingleProduct(product);
		}
	}

	public List<Product> getProductsByBrand(String brand) {
		List<ProductBrand> matchedBrands = productBrandRepository.findByNameContainingIgnoreCase(brand).orElse(null);
		if (!CollectionUtils.isEmpty(matchedBrands)) {
			List<Long> brandIds = matchedBrands.stream().map(ProductBrand::getId).collect(toList());
			List<Product> products = productRepository.findAllByBrandIdIn(brandIds);
			enrichMultipleProducts(products);
			return products;
		}
		return null;
	}

	public Map<String, List<Product>> getAllProductsGroupByBrand() {
		List<Product> allProducts = getAllProducts();
		List<ProductBrand> allBrands = productBrandRepository.findAll();
		Map<Long, String> brandMap = allBrands.stream().collect(toMap(ProductBrand::getId, ProductBrand::getName));

		Function<Product, String> getBrandNameById = prd -> brandMap.getOrDefault(prd.getBrandId(), "Unknown Brand");
		Map<String, List<Product>> allProductsByBrand = allProducts.stream().collect(groupingBy(getBrandNameById));
		return allProductsByBrand;
	}

	public Map<Integer, Integer> getProductsInStockBySeller(String sellerId) {
		List<ProductStocks> productStockBySeller = productStocksRepository.findAllBySellerId(Integer.parseInt(sellerId));
		Map<Integer, Integer> productQuantityMap = productStockBySeller.stream().collect(toMap(ProductStocks::getProductId, ProductStocks::getQuantity));
		return productQuantityMap;
	}
}