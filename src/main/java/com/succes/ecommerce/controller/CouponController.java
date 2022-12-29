package com.succes.ecommerce.controller;

import com.succes.ecommerce.Repository.CouponRepo;
import com.succes.ecommerce.Repository.OrderRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.model.Coupon;
import com.succes.ecommerce.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/coupon")
public class CouponController {

	@Autowired
	CouponRepo couponRepo;

	@RequestMapping("getAll")
	public List<Coupon> getAllCoupon(){
		return couponRepo.findAll();}
	@GetMapping("/{couponId}")
	public Optional<Coupon> findCoupon(@PathVariable Long couponId) {


		return couponRepo.findById(couponId);

	}
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCoupon(@RequestBody Coupon coupon) {
		couponRepo.save(coupon);
		return ResponseEntity.ok(new ApiResponse("Coupon created successfully", ""));
	}
	@PutMapping("/update/{couponId}")
	public ResponseEntity<ApiResponse> updateCoupon(@PathVariable("couponId") Long couponId, @RequestBody Coupon coupon) throws Exception {

		Optional<Coupon> optionalCoupon = couponRepo.findById(couponId);
		// throw an exception if product does not exists
		if (!optionalCoupon.isPresent()) {
			throw new Exception("Coupon not present");
		}
		Coupon coupons = optionalCoupon.get();
		coupons.setKey(coupon.getKey());
		coupons.setActive(coupon.isActive());
		coupons.setDiscountAmount(coupon.getDiscountAmount());
		coupons.setExpDate(coupon.getExpDate());
//		coupons.setShop_coup(coupon.getShop_coup());

		couponRepo.save(coupons);
		return ResponseEntity.ok(new ApiResponse("Coupon Updated successfully", ""));
	}

	@DeleteMapping("/delete/{couponId}")
	public ResponseEntity<ApiResponse> deleteCoupon(@PathVariable("couponId") Long couponId ){





		couponRepo.deleteById(couponId);

		return ResponseEntity.ok(new ApiResponse(" Coupon Deleted successfully", ""));

	}


}
