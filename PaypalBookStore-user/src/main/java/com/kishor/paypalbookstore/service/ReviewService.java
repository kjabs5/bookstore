package com.kishor.paypalbookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kishor.paypalbookstore.entity.Review;

public interface ReviewService {
   
	public Page<Review> getAllReviews(int pageNumber);
	
	public void saveReview(Review theReview);
	
	 public List<Review> mostRecentReviews();

	public void deleteReview(int id);
}
