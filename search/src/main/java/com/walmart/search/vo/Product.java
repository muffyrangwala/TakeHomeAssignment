package com.walmart.search.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {

	private String productId;
	private String productName;
	private String shortDescription;
	private String longDescription;
	private String price;
	private Double doublePrice;
	private String productImage;
	private Double reviewRating;
	private Integer reviewCount;
	private Boolean inStock = false;

	public Product() {
		super();
	}
	
	public Product(Product product) {
		super();
		this.productId = product.getProductId();
		this.productName = product.getProductId();
		this.shortDescription = product.getShortDescription();
		this.longDescription = product.getLongDescription();
		this.price = product.getPrice();
		this.doublePrice = product.getDoublePrice();
		this.productImage = product.getProductImage();
		this.reviewRating = product.getReviewRating();
		this.reviewCount = product.getReviewCount();
		this.inStock = product.getInStock();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(Double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	@JsonIgnore
	public Double getDoublePrice() {
		return doublePrice;
	}

	@JsonIgnore
	public void setDoublePrice(Double doublePrice) {
		this.doublePrice = doublePrice;
	}
}
