package com.walmart.search.service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.walmart.search.utils.ProductClientConfig;
import com.walmart.search.vo.Product;
import com.walmart.search.vo.ProductsClientResponse;

@Service
public class ProductDataService {

	private List<Product> products = new ArrayList<>();
	private ProductClientConfig config;
	private final String SEPARATOR = "/";
	Logger logger = LoggerFactory.getLogger(ProductDataService.class);

	@Autowired
	public ProductDataService(ProductClientConfig config) {
		super();
		this.config = config;
	}

	/**
	 * Method to retrieve data from source API. 
	 * It will run every 10 mins to refresh data from source.
	 */
	@PostConstruct
	@Scheduled(fixedRateString = "${product.cache.refresh}")
	private void init(){
		loadProductsList();  
	}

	private void loadProductsList() {

		try {
			List<Product> tempList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append(config.getClientUrl());
			sb.append(config.getProducts());
			sb.append(config.getFirstRequest());

			ProductsClientResponse response = getData(sb.toString());
			tempList.addAll(response.getProducts());
			int totalProducts = response.getTotalProducts();
			int numberOfCalls = totalProducts / config.getMaxPageSize();
			if (totalProducts % config.getMaxPageSize() != 0) {
				numberOfCalls += 1;
			}

			sb = new StringBuilder();
			for (int i = 2; i <= numberOfCalls; i++) {
				sb.append(config.getClientUrl());
				sb.append(config.getProducts());
				sb.append(i);
				sb.append(SEPARATOR);
				sb.append(config.getMaxPageSize());
				sb.append(SEPARATOR);
				response = getData(sb.toString());
				tempList.addAll(response.getProducts());
				sb = new StringBuilder();
			}

			processAmounts(tempList);
			products = new ArrayList<>(tempList);
		} catch(Exception e) {
			// log the error and let the api start.
			logger.error("Unable to load product information", e);
		}
	}

	private void processAmounts(List<Product> list) {
		for (Product product : list) {
			try {
				Locale locale = new Locale("en", "US");
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
				Double price = currencyFormatter.parse(product.getPrice()).doubleValue();
				product.setDoublePrice(price);
			} catch (ParseException e) {
				product.setDoublePrice(0.0);
			}
		}
	}

	private ProductsClientResponse getData(String restUrl) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(restUrl , ProductsClientResponse.class);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
