package com.walmart.search;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.walmart.search.client.ProductRestClient;
import com.walmart.search.service.ProductDataService;
import com.walmart.search.utils.ProductClientConfig;
import com.walmart.search.vo.Product;
import com.walmart.search.vo.ProductsClientResponse;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductDataServiceTest {

	@Mock
	private ProductRestClient client;
	
	@Mock
	private RestTemplate restTemplate;
	
	private ProductDataService dataService;
	ProductClientConfig config;
	
	@Before
	public void setup() {
		config = new ProductClientConfig("mock", "/walmartproducts/", 30, "/1/30");
		client = new ProductRestClient(restTemplate);
		dataService = new ProductDataService(config, client);
	}
	
	private ProductsClientResponse getClientResponse() {
		ProductsClientResponse response = new ProductsClientResponse();
		
		response.setPageNumber(1);
		response.setPageSize(3);
		response.setStatusCode("200");
		response.setTotalProducts(3);
		response.setProducts(getSampleProducts());
		return response;
	}

	@Test
	public void testLoadProductsFromCLient() {
		ProductsClientResponse response = getClientResponse();
		StringBuilder sb = new StringBuilder();
		sb.append(config.getClientUrl());
		sb.append(config.getProducts());
		sb.append(config.getFirstRequest());
		
		when(client.callProductClient(sb.toString())).thenReturn(response);
		dataService.init();
		Assertions.assertThat(dataService.getProducts().size() == 3);
		Assertions.assertThat(dataService.getProducts().get(1).getDoublePrice().equals(0.0));
	}
	
	private List<Product> getSampleProducts() {
		List<Product> products = new ArrayList<>();
		
		Product product = new Product();
		product.setProductId("111-111");
		product.setDoublePrice(5.0);
		product.setPrice("Invalid data");
		product.setInStock(true);
		product.setProductName("HDMI cable");
		product.setReviewCount(3);
		product.setReviewRating(3.9);
		products.add(product);
		
		product = new Product();
		product.setProductId("222-222");
		product.setDoublePrice(10.0);
		product.setInStock(false);
		product.setProductName("HDMI cable Samsung");
		product.setReviewCount(5);
		product.setReviewRating(4.9);	
		products.add(product);
		
		product = new Product();
		product.setProductId("333-333");
		product.setDoublePrice(20.0);
		product.setInStock(true);
		product.setProductName("HDMI cable Emerson");
		product.setReviewCount(4);
		product.setReviewRating(4.5);	
		products.add(product);
		
		return products;
	}
}
