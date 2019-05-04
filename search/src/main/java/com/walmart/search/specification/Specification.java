package com.walmart.search.specification;

import com.walmart.search.vo.Product;

public interface Specification {

	boolean isMatch(Product product);
}
