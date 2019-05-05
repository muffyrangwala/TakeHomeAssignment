package com.walmart.search.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.walmart.search.vo.ProductsClientResponse;

@Service
public class ProductRestClient {

	private RestTemplate restTemplate;

	@Autowired
	public ProductRestClient(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
	
	public ProductsClientResponse callProductClient(String url) {
		return restTemplate.getForObject(url , ProductsClientResponse.class);
	}
}
