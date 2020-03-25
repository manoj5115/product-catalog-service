package com.bleedev.sapient;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bleedev.sapient.controller.ProductApi;

@SpringBootTest
class ProductServiceApplicationTests {
	
	@Autowired
	private ProductApi productApi;

	@Test
	public void contexLoads() throws Exception {
		assertThat(productApi).isNotNull();
	}
}
