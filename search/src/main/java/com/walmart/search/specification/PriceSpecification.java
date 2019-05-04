package com.walmart.search.specification;

import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Product;

public class PriceSpecification implements Specification {

	private Operator operator;
	private Double target;

	public PriceSpecification(Operator operator, Double target) {
		this.operator = operator;
		this.target = target;
	}
	
	public PriceSpecification() {
		super();
	}

	@Override
	public boolean isMatch(Product product) {
		
		switch (operator) {
		case maximum:
			if (product.getDoublePrice().compareTo(this.target) > 0) {
				return false;
			}
			break;
		case minimum:
			if (product.getDoublePrice().compareTo(this.target) < 0) {
				return false;
			}
			break;
		default:
			return false;
			
		}
		return true;
	}
}
