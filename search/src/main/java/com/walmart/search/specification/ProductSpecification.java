package com.walmart.search.specification;

import java.util.Iterator;
import java.util.List;

import com.walmart.search.vo.Product;

public class ProductSpecification implements Specification {

	private List<Specification> specification;

	public ProductSpecification(List<Specification> specification) {
		super();
		this.specification = specification;
	}

	@Override
	public boolean isMatch(Product product) {
		Iterator<Specification> iterator = specification.iterator();
		while (iterator.hasNext()) {
			if (!iterator.next().isMatch(product)) {
				return false;
			}
		}
		return true;
	}
}
