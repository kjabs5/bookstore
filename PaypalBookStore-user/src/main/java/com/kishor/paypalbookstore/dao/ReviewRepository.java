package com.kishor.paypalbookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kishor.paypalbookstore.entity.Review;

public interface ReviewRepository  extends JpaRepository<Review, Integer> {

	
	@Query(value="SELECT * FROM review ORDER BY text_time DESC limit 4",nativeQuery=true)
    public List<Review> mostRecentReviews();
}
