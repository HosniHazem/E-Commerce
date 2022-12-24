package com.succes.ecommerce.controller;

import com.succes.ecommerce.JWTConfiguration.ShoppingConfiguration;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.model.AddtoCart;
import com.succes.ecommerce.service.CartService.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/addtocart")
public class AddtoCartController {

	@Autowired
	CartService cartService;
	@RequestMapping("addProduct")
	public ResponseEntity<?> addCartwithProduct(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"productId","user_id","qty","price"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			long productId = Long.parseLong(addCartRequest.get("productId"));
			long user_id =  Long.parseLong(addCartRequest.get("user_id"));
			int qty =  Integer.parseInt(addCartRequest.get("qty"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			List<AddtoCart> obj = cartService.addCartbyUserIdAndProductId(productId,user_id,qty,price);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}

	@RequestMapping("updateQtyForCart")
	public ResponseEntity<?> updateQtyForCart(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"id","user_id","qty","price"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {

			}
			long id = Long.parseLong(addCartRequest.get("id"));
			long user_id =  Long.parseLong(addCartRequest.get("user_id"));
			int qty =  Integer.parseInt(addCartRequest.get("qty"));
			double price = Double.parseDouble(addCartRequest.get("price"));
			cartService.updateQtyByCartId(id, qty, price);
			List<AddtoCart> obj = cartService.getCartByUserId(user_id);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}

	}


	@RequestMapping("removeProductFromCart")
	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
		try {
			String keys[] = {"user_id","id"};
			if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {

			}
			List<AddtoCart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("id")), Long.parseLong(removeCartRequest.get("user_id")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}

	@RequestMapping("getCartsByUserId")
	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
		try {
			String keys[] = {"user_id"};
			if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
			}
			List<AddtoCart> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("user_id")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}
