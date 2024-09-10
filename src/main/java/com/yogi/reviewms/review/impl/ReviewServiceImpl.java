package com.yogi.reviewms.review.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yogi.reviewms.review.Review;
import com.yogi.reviewms.review.ReviewRepo;
import com.yogi.reviewms.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepo reviewRepo;
 


  @Override
  public List<Review> getAllReviews(Long companyId){
     List<Review>reviews=reviewRepo.findByCompanyId(companyId);
     return reviews;
  }

@Override
public boolean addReview(Long companyId, Review review) {

    if(companyId!=null && review!=null ){
      review.setCompanyId(companyId);
      reviewRepo.save(review);
      return true;
    } 
    return false;
}

@Override
public Review getReview(Long reviewId) {
return reviewRepo.findById(reviewId).orElse(null);
  
//  return reviews.stream().filter(review->review.getId().equals(reviewId)).findFirst().orElse(null);
  // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'getReview'");
}

@Override
public boolean updateReview( Long reviewId, Review review) {
  Review r2 =reviewRepo.findById(reviewId).orElse(null);
  if(r2!=null){
        r2.setTitle(review.getTitle());
        r2.setDescription(review.getDescription());
        r2.setRating(review.getRating());
        r2.setCompanyId(review.getCompanyId());

        reviewRepo.save(r2);
        return true;
    }
  return false;
}

@Override
public boolean deleteReview( Long reviewId) {
    Review r2 =reviewRepo.findById(reviewId).orElse(null);
  if(r2!=null){
        reviewRepo.delete(r2);
        return true;
  }
  return false;

}
}
