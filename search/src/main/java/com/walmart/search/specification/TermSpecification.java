package com.walmart.search.specification;

import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Product;

public class TermSpecification implements Specification {

	private Operator operator;
	private String target;

	public TermSpecification(Operator operator, String target) {
		this.operator = operator;
		this.target = target;
	}

	@Override
	public boolean isMatch(Product product) {
		
		switch (operator) {
		case contains:
			if (product.getProductName() == null
				|| !product.getProductName().toLowerCase().contains(target.toLowerCase())) {
				return false;
			}
			break;
		default:
			return false;
			
		}
		return true;
	}

}
