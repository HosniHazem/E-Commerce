package com.succes.ecommerce.controller;

import com.succes.ecommerce.Repository.OrderRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;

import com.succes.ecommerce.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrdersController {

	@Autowired
	OrderRepo orderRepo;

	@RequestMapping("getAll")
	public List<Order> getAllOrders(){
		return orderRepo.findAll();}
	@GetMapping("/{orderId}")
	public Optional<Order> findProduct(@PathVariable Long orderId) {


		return orderRepo.findById(orderId);

	}
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order) {
		orderRepo.save(order);
		return ResponseEntity.ok(new ApiResponse("Order created successfully", ""));
	}
	@PutMapping("/update/{orderId}")
	public ResponseEntity<ApiResponse> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) throws Exception {

		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		// throw an exception if product does not exists
		if (!optionalOrder.isPresent()) {
			throw new Exception("order not present");
		}
		Order orders = optionalOrder.get();
		orders.setStatus(order.getStatus());
		orders.setProducts(order.getProducts());
		orders.setUser(order.getUser());

		orderRepo.save(orders);
		return ResponseEntity.ok(new ApiResponse("Order Updated successfully", ""));
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("orderId") Long orderId ){





		orderRepo.deleteById(orderId);

		return ResponseEntity.ok(new ApiResponse(" order Deleted successfully", ""));

	}


}
