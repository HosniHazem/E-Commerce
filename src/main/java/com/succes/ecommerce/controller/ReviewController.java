package com.succes.ecommerce.controller;

import com.succes.ecommerce.Repository.OrderRepo;
import com.succes.ecommerce.Repository.ReviewRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.model.Order;
import com.succes.ecommerce.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/review")
public class ReviewController {

	@Autowired
	ReviewRepo reviewRepo;

	@RequestMapping("getAll")
	public List<Review> getAllReview(){
		return reviewRepo.findAll();}
	@GetMapping("/{reviewId}")
	public Optional<Review> findReview(@PathVariable Long reviewId) {


		return reviewRepo.findById(reviewId);

	}
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createReview(@RequestBody Review review) {
		reviewRepo.save(review);
		return ResponseEntity.ok(new ApiResponse("Order created successfully", ""));
	}
	@PutMapping("/update/{reviewId}")
	public ResponseEntity<ApiResponse> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody Review review) throws Exception {

		Optional<Review> optionalReview = reviewRepo.findById(reviewId);
		// throw an exception if product does not exists
		if (!optionalReview.isPresent()) {
			throw new Exception("Review not present");
		}
		Review reviews = optionalReview.get();
		reviews.setRate(review.getRate());
		reviews.setDescription(review.getDescription());
		reviews.setUser(review.getUser());


		reviewRepo.save(reviews);
		return ResponseEntity.ok(new ApiResponse("Review Updated successfully", ""));
	}

	@DeleteMapping("/delete/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReview(@PathVariable("reviewId") Long reviewId ){





		reviewRepo.deleteById(reviewId);

		return ResponseEntity.ok(new ApiResponse(" Review Deleted successfully", ""));

	}


}
