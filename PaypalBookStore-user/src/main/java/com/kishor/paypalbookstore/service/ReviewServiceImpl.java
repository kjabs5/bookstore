package com.kishor.paypalbookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.ReviewRepository;
import com.kishor.paypalbookstore.entity.Review;


@Service
public class ReviewServiceImpl implements ReviewService {
	

@Autowired 
private ReviewRepository reviewRepository;
	
    @Override  
	public Page<Review> getAllReviews(int pageNumber){
	Pageable pageable=PageRequest.of(pageNumber-1,5);
	return reviewRepository.findAll(pageable);
	}
	
    @Override
	public void saveReview(Review theReview)
	{
		reviewRepository.save(theReview);
	}
    @Override
	public List<Review> mostRecentReviews() {
		return reviewRepository.mostRecentReviews();
	}

	@Override
	public void deleteReview(int id) {
		reviewRepository.deleteById(id);
		
	}

}
