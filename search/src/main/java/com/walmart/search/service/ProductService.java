package com.walmart.search.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.search.exception.ProductException;
import com.walmart.search.specification.ProductSpecification;
import com.walmart.search.specification.ProductSpecificationBuilder;
import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Filters;
import com.walmart.search.vo.Product;

@Service
public class ProductService {

	private ProductDataService dataService;

	@Autowired
	public ProductService(ProductDataService dataService) {
		this.dataService = dataService;
	}

	public List<Product> searchProducts(Filters filters) throws ProductException {
		List<Product> result = new ArrayList<>();
		List<Product> products = dataService.getProducts();
		if (products.isEmpty()) {
			throw new ProductException("Source product data is not available.");
		}
		boolean valid = validateSearchParamters(filters);
		if (valid) {
			ProductSpecification criteria = buildCriteria(filters);
			for (Product product: products) {
				if (criteria.isMatch(product)) {
					result.add(product);
				}
			}
		}
		return result;
	}

	private ProductSpecification buildCriteria(Filters filters) {
		ProductSpecificationBuilder builder = new ProductSpecificationBuilder();
		builder.withPrice()
		.being(Operator.minimum).value(filters.getMinPrice())
		.and().withPrice()
		.being(Operator.maximum).value(filters.getMaxPrice())
		.and().withReviewRating()
		.being(Operator.minimum).value(filters.getMinReviewRating())
		.and().withReviewRating()
		.being(Operator.maximum).value(filters.getMaxReviewRating())
		.and().withReviewCount()
		.being(Operator.minimum).value(filters.getMinReviewCount())
		.and().withReviewCount()
		.being(Operator.maximum).value(filters.getMaxReviewCount())
		.and().withStock()
		.being(Operator.instock).value(filters.getInStock())
		.and().withTerms()
		.being(Operator.contains).value(filters.getTerms());

		return builder.build();
	}

	private boolean validateSearchParamters(Filters filter) {

		if (filter.getMaxPrice() != null 
				&& filter.getMinPrice() != null
				&& filter.getMaxPrice().compareTo(filter.getMinPrice()) < 0) {
			throw new IllegalArgumentException("Minimum price cannot be greater than Maximum price");
		} else if (filter.getMaxReviewCount() != null
				&& filter.getMinReviewCount() != null
				&& filter.getMaxReviewCount().compareTo(filter.getMinReviewCount()) < 0) {
			throw new IllegalArgumentException("Minimum review count cannot be greater than Maximum review count");
		} else if (filter.getMaxReviewRating() != null
				&& filter.getMinReviewRating() != null
				&& filter.getMaxReviewRating().compareTo(filter.getMinReviewRating()) < 0) {
			throw new IllegalArgumentException("Minimum review rating cannot be greater than Maximum review rating");
		}

		return true;
	}
}