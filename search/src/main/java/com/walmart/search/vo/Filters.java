package com.walmart.search.vo;

public class Filters {

	private Double minPrice;
	private Double maxPrice;
	private Double minReviewRating;
	private Double maxReviewRating;
	private Integer minReviewCount;
	private Integer maxReviewCount;
	private Boolean inStock;
	private String terms;

	public Filters(Double minPrice, Double maxPrice, Double minReviewRating, Double maxReviewRating,
			Integer minReviewCount, Integer maxReviewCount, Boolean inStock, String terms) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minReviewRating = minReviewRating;
		this.maxReviewRating = maxReviewRating;
		this.minReviewCount = minReviewCount;
		this.maxReviewCount = maxReviewCount;
		this.inStock = inStock;
		this.terms = terms;
	}

	public Filters() {
		super();
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinReviewRating() {
		return minReviewRating;
	}

	public void setMinReviewRating(Double minReviewRating) {
		this.minReviewRating = minReviewRating;
	}

	public Double getMaxReviewRating() {
		return maxReviewRating;
	}

	public void setMaxReviewRating(Double maxReviewRating) {
		this.maxReviewRating = maxReviewRating;
	}

	public Integer getMinReviewCount() {
		return minReviewCount;
	}

	public void setMinReviewCount(Integer minReviewCount) {
		this.minReviewCount = minReviewCount;
	}

	public Integer getMaxReviewCount() {
		return maxReviewCount;
	}

	public void setMaxReviewCount(Integer maxReviewCount) {
		this.maxReviewCount = maxReviewCount;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public Boolean getInStock() {
		return inStock;
	}

	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
}
