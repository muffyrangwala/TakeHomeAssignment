package com.walmart.search.specification;

import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Product;

public class ReviewCountSpecification implements Specification {

	private Operator operator;
	private Integer target;

	public ReviewCountSpecification(Operator operator, Integer target) {
		this.operator = operator;
		this.target = target;
	}
	
	@Override
	public boolean isMatch(Product product) {	
		switch (operator) {
		case maximum:
			if (product.getReviewCount().compareTo(target) > 0) {
				return false;
			}
			break;
		case minimum:
			if (product.getReviewCount().compareTo(target) < 0) {
				return false;
			}
			break;
		default:
			return false;
			
		}
		return true;
	}
}
