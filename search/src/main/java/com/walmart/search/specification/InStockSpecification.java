package com.walmart.search.specification;

import com.walmart.search.specification.ProductSpecificationBuilder.Operator;
import com.walmart.search.vo.Product;

public class InStockSpecification implements Specification {

	private Operator operator;
	private boolean target;

	public InStockSpecification(Operator operator, Boolean target) {
		this.operator = operator;
		this.target = target;
	}

	@Override
	public boolean isMatch(Product product) {
		
		switch (operator) {
		case instock:
			if (!product.getInStock().equals(this.target)){
				return false;
			}
			break;
		default:
			return false;
			
		}
		return true;
	}
}
