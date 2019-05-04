package com.walmart.search;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.walmart.search.exception.ProductException;
import com.walmart.search.service.ProductDataService;
import com.walmart.search.service.ProductService;
import com.walmart.search.utils.ProductClientConfig;
import com.walmart.search.vo.Filters;
import com.walmart.search.vo.Product;

@RunWith(MockitoJUnitRunner.class)
public class SpecificationSearchTest {

	@Mock
	private ProductClientConfig config;
	
	private ProductDataService dataService;
	private ProductService productService;
	
	@Before
	public void setup() {
		dataService = new ProductDataService(config);
		List<Product> products = getSampleInput();
		dataService.setProducts(products);
		productService = new ProductService(dataService);
	}

	@Test
	public void searchMaxPrice() throws ProductException {
		Filters filters = new Filters();
		filters.setMaxPrice(5.0);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("111-111"));
	}
	
	@Test
	public void searchMinPrice() throws ProductException {
		Filters filters = new Filters();
		filters.setMinPrice(10.0);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("333-333"));
	}
	
	@Test
	public void searchBetweenPrice() throws ProductException {
		Filters filters = new Filters();
		filters.setMinPrice(5.0);
		filters.setMaxPrice(10.0);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 2);
		Assertions.assertThat(products.get(0).getProductId().equals("111-111"));
		Assertions.assertThat(products.get(1).getProductId().equals("222-222"));
	}
	
	@Test
	public void searchBetweenRating() throws ProductException {
		Filters filters = new Filters();
		filters.setMinReviewRating(4.0);
		filters.setMaxReviewRating(4.5);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("222-222"));
	}
	
	@Test
	public void searchBetweenReviewCount() throws ProductException {
		Filters filters = new Filters();
		filters.setMinReviewCount(4);
		filters.setMaxReviewCount(5);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 2);
		Assertions.assertThat(products.get(0).getProductId().equals("222-222"));
		Assertions.assertThat(products.get(0).getProductId().equals("333-333"));
	}
	
	@Test
	public void searchInStock() throws ProductException {
		Filters filters = new Filters();
		filters.setInStock(true);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 2);
		Assertions.assertThat(products.get(0).getProductId().equals("111-111"));
		Assertions.assertThat(products.get(0).getProductId().equals("333-333"));
	}
	
	@Test
	public void searchNotInStock() throws ProductException {
		Filters filters = new Filters();
		filters.setInStock(false);
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("222-222"));
	}
	
	@Test
	public void searchTerms() throws ProductException {
		Filters filters = new Filters();
		filters.setTerms("Samsung");
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("222-222"));
	}
	
	@Test
	public void searchAllCombinations() throws ProductException {
		Filters filters = new Filters();
		filters.setMinPrice(5.0);
		filters.setMaxPrice(10.0);
		filters.setMinReviewRating(4.5);
		filters.setMaxReviewRating(5.0);
		filters.setMinReviewCount(4);
		filters.setMaxReviewCount(5);
		filters.setTerms("Samsung");
		List<Product> products = productService.searchProducts(filters);
		Assertions.assertThat(products.size() == 1);
		Assertions.assertThat(products.get(0).getProductId().equals("222-222"));
	}
	
	private List<Product> getSampleInput() {
		List<Product> products = new ArrayList<>();
		
		Product product = new Product();
		product.setProductId("111-111");
		product.setDoublePrice(5.0);
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
