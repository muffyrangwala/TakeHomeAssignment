package com.walmart.search.specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationBuilder {

	protected List<Specification> specifications = new ArrayList<>();

	private PriceSpecificationBuilder priceCriteriaBuilder;
	private ReviewRatingSpecificationBuilder reviewRatingCriteriaBuilder;
	private ReviewCountSpecificationBuilder reviewCountCriteriaBuilder;
	private InStockSpecificationBuilder instockSpecificationBuilder;
	private TermSpecificationBuilder termSpecificationBuilder;
	
	public enum Operator{
		minimum,
		maximum, 
		instock,
		contains
	}

	public PriceSpecificationBuilder withPrice() {
		if (priceCriteriaBuilder == null) {
			priceCriteriaBuilder = new PriceSpecificationBuilder();
		}
		return this.priceCriteriaBuilder;
	}
	
	public ReviewRatingSpecificationBuilder withReviewRating() {
		if (reviewRatingCriteriaBuilder == null) {
			reviewRatingCriteriaBuilder = new ReviewRatingSpecificationBuilder();
		}
		return this.reviewRatingCriteriaBuilder;
	}
	
	public ReviewCountSpecificationBuilder withReviewCount() {
		if (reviewCountCriteriaBuilder == null) {
			reviewCountCriteriaBuilder = new ReviewCountSpecificationBuilder();
		}
		return this.reviewCountCriteriaBuilder;
	}
	
	public InStockSpecificationBuilder withStock() {
		if (instockSpecificationBuilder == null) {
			instockSpecificationBuilder = new InStockSpecificationBuilder();
		}
		return this.instockSpecificationBuilder;
	}
	
	public TermSpecificationBuilder withTerms() {
		if (termSpecificationBuilder == null) {
			termSpecificationBuilder = new TermSpecificationBuilder();
		}
		return this.termSpecificationBuilder;
	}

	public ProductSpecificationBuilder and() {
		return ProductSpecificationBuilder.this;
	}

	public ProductSpecification build() {
		return new ProductSpecification(specifications);
	}
	
	public class PriceSpecificationBuilder {


		private Operator operator;
		private double target;
		
		public PriceSpecificationBuilder being(Operator operator) {
			this.operator = operator;
			return PriceSpecificationBuilder.this;
		}
		
		public ProductSpecificationBuilder value(Double target) {
			if (target != null) {
				this.target = target;
				ProductSpecificationBuilder.this.specifications.add(new PriceSpecification(operator,this.target));
			}
			return ProductSpecificationBuilder.this ;
		}
	}
	
	public class ReviewCountSpecificationBuilder {

		private Operator operator;
		private Integer target;
		
		public ReviewCountSpecificationBuilder being(Operator operator) {
			this.operator = operator;
			return ReviewCountSpecificationBuilder.this;
		}
		
		public ProductSpecificationBuilder value(Integer target) {
			if (target != null) {
				this.target = target;
				ProductSpecificationBuilder.this.specifications.add(new ReviewCountSpecification(operator,this.target));
			}
			return ProductSpecificationBuilder.this ;
		}
	}
	
	public class ReviewRatingSpecificationBuilder {

		private Operator operator;
		private Double target;
		
		public ReviewRatingSpecificationBuilder being(Operator operator) {
			this.operator = operator;
			return ReviewRatingSpecificationBuilder.this;
		}
		
		public ProductSpecificationBuilder value(Double target) {
			if (target != null) {
				this.target = target;
				ProductSpecificationBuilder.this.specifications.add(new ReviewRatingSpecification(operator,this.target));
			}
			return ProductSpecificationBuilder.this ;
		}
	}
	
	public class InStockSpecificationBuilder {

		private Operator operator;
		private boolean target;
		
		public InStockSpecificationBuilder being(Operator operator) {
			this.operator = operator;
			return InStockSpecificationBuilder.this;
		}
		
		public ProductSpecificationBuilder value(Boolean target) {
			if (target != null) {
				this.target = target;
				ProductSpecificationBuilder.this.specifications.add(new InStockSpecification(operator,this.target));
			}
			return ProductSpecificationBuilder.this ;
		}
	}
	
	public class TermSpecificationBuilder {

		private Operator operator;
		private String target;
		
		public TermSpecificationBuilder being(Operator operator) {
			this.operator = operator;
			return TermSpecificationBuilder.this;
		}
		
		public ProductSpecificationBuilder value(String target) {
			if (target != null) {
				this.target = target;
				ProductSpecificationBuilder.this.specifications.add(new TermSpecification(operator,this.target));
			}
			return ProductSpecificationBuilder.this ;
		}
	}
}
