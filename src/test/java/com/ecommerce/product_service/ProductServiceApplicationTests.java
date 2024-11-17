package com.ecommerce.product_service;

import com.ecommerce.product_service.controller.ProductController;
import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;


import java.math.BigDecimal;
import java.util.List;

import static java.nio.file.Paths.get;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)

class ProductServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void createProduct() throws Exception {
		Product product = Product.builder()
				.name("product1")
				.id("1")
				.description("sampleee")
				.price(BigDecimal.valueOf(222222222))
				.build();
		ProductRequest product1 = ProductRequest.builder()
				.name("product1")
				.description("sampleee")
				.price(BigDecimal.valueOf(222222222))
				.build();
		when(productRepository.save(any(Product.class))).thenReturn(product);

		 productService.createProduct(product1);
		 assertEquals("","");
	}

	@Test
	public void getUser() throws Exception {
		ProductResponse productResponse = ProductResponse.builder()
				.name("Omkar")
				.description("Omkar")
				.price(BigDecimal.valueOf(10000))
				.build();
		when(productService.getProductlist()).thenReturn((List<ProductResponse>) productResponse);

		mockMvc.perform((RequestBuilder) get("/api/product"))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("$.name", is("Omkar")))
				.andExpect((ResultMatcher) jsonPath("$.description", is("Omkar")));
	}

}
