package com.walmart.search.specification;

import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Product;

public class ReviewRatingSpecification implements Specification {

	private Operator operator;
	private double target;

	public ReviewRatingSpecification(Operator operator, Double target) {
		this.operator = operator;
		this.target = target;
	}

	@Override
	public boolean isMatch(Product product) {
		switch (operator) {
		case maximum:
			if (product.getReviewRating().compareTo(this.target) > 0) {
				return false;
			}
			break;
		case minimum:
			if (product.getReviewRating().compareTo(this.target) < 0) {
				return false;
			}
			break;
		default:
			return false;
			
		}
		return true;
	}
}
