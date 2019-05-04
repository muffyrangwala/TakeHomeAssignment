package com.walmart.search;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.walmart.search.vo.ErrorDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductSearchIntegerationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@SuppressWarnings("rawtypes")
    public void search() throws Exception {  
		ResponseEntity<ArrayList> response = restTemplate.getForEntity("/search", ArrayList.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
	
	@Test
	@SuppressWarnings("rawtypes")
    public void searchWithPriceFilters() throws Exception {  
		ResponseEntity<ArrayList> response = restTemplate.getForEntity("/search?minPrice=1&maxPrice=20", ArrayList.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
	
	@Test
	@SuppressWarnings("rawtypes")
    public void searchWithRatingFilters() throws Exception {  
		ResponseEntity<ArrayList> response = restTemplate.getForEntity("/search?minReviewRating=1&maxReviewRating=5", ArrayList.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
	
	@Test
	@SuppressWarnings("rawtypes")
    public void searchWithCountFilters() throws Exception {  
		ResponseEntity<ArrayList> response = restTemplate.getForEntity("/search?minReviewCount=1&maxReviewCount=20&minReviewRating=1&maxReviewRating=5&minPrice=1&maxPrice=20", ArrayList.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
	
	@Test
	@SuppressWarnings("rawtypes")
    public void searchWithAllFilters() throws Exception {  
		ResponseEntity<ArrayList> response = restTemplate.getForEntity("/search?minReviewCount=1&maxReviewCount=20", ArrayList.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
	
	@Test
    public void searchIllegalArguments() throws Exception {  
		ResponseEntity<ErrorDetails> response = restTemplate.getForEntity("/search?minPrice=10&maxPrice=0", ErrorDetails.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }
	
	@Test
    public void searchMethodArgumentTypeMismatch() throws Exception {  
		ResponseEntity<ErrorDetails> response = restTemplate.getForEntity("/search?minPrice=abs", ErrorDetails.class);
		Assertions.assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }
}
