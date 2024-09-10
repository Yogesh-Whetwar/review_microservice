package com.yogi.reviewms.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yogi.reviewms.review.impl.ReviewServiceImpl;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
   
@Autowired
private ReviewServiceImpl reviewService;

@GetMapping
public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
    return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
}

@PostMapping
public ResponseEntity<String> addReview(@RequestParam  Long companyId, @RequestBody Review review){
  boolean isReviewsaved =  reviewService.addReview(companyId, review);
  if(isReviewsaved){
      return new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
    }else{
      return new ResponseEntity<>("Review Not saved Successfully",HttpStatus.NOT_FOUND);

  }
}  

@GetMapping("/{reviewId}")
public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
  
    return new ResponseEntity<> (reviewService.getReview( reviewId),HttpStatus.OK);
}   

@PutMapping("/{reviewId}")
public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review){
   boolean updation=reviewService.updateReview( reviewId, review);
   if(updation){
    return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
}
return new ResponseEntity<>("Review doesnt Updated",HttpStatus.NOT_FOUND);
}  

@DeleteMapping("/{reviewId}")
public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
     boolean deletetion=reviewService.deleteReview(reviewId);
     if(deletetion){
        return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
     }
     return new ResponseEntity<>("Review cant be deleted",HttpStatus.NOT_FOUND);
}
}

