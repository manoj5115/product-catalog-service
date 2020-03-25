package com.bleedev.sapient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllProductsTest() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].sku").isNotEmpty());
	}

	@Test
	public void getProductBySkuTest() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products/101").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.sku").value(101));
	}
	
	@Test
	public void getAllProductsGroupByBrandTest() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products/brand").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.Nike.[*].sku").isNotEmpty());
	}
	
	@Test
	public void getProductsByBrand() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products/brand/nike").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.[*].sku").isNotEmpty());
	}
	
	@Test
	public void getProductsInStockBySeller() throws Exception {
		this.mockMvc.perform(get("/productApi/v1/products/stock/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	private String asJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(object);
		return jsonString;

	}
}