package com.walmart.search.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductClientConfig {

	private final String clientUrl;
	private final String products;
	public final Integer maxPageSize;
	private final String firstRequest;

	@Autowired
	public ProductClientConfig(@Value("${client.url}") String clientUrl, 
			@Value("${products.endpoint}") String products,
			@Value("${max.page.size}") Integer maxPageSize,
			@Value("${first.request}") String firstRequest) {
		super();
		this.clientUrl = clientUrl;
		this.products = products;
		this.maxPageSize = maxPageSize;
		this.firstRequest = firstRequest;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public String getProducts() {
		return products;
	}

	public Integer getMaxPageSize() {
		return maxPageSize;
	}

	public String getFirstRequest() {
		return firstRequest;
	}
}
