package com.walmart.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.search.service.ProductService;
import com.walmart.search.vo.Filters;
import com.walmart.search.vo.Product;

@RestController
public class ProductSearchController {

	private ProductService productService;

	@Autowired
	public ProductSearchController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam(value = "minPrice", required = false) Double minPrice,
			@RequestParam(value = "maxPrice", required = false) Double maxPrice,
			@RequestParam(value = "minReviewRating", required = false) Double minReviewRating,
			@RequestParam(value = "maxReviewRating", required = false) Double maxReviewRating,
			@RequestParam(value = "minReviewCount", required = false) Integer minReviewCount,
			@RequestParam(value = "maxReviewCount", required = false) Integer maxReviewCount,
			@RequestParam(value = "inStock", required = false) Boolean inStock,
			@RequestParam(value = "terms", required = false) String terms) throws Exception {

		Filters filters = new Filters(minPrice, maxPrice, minReviewRating, maxReviewRating, minReviewCount,
				maxReviewCount, inStock, terms);

		return productService.searchProducts(filters);
	}
}