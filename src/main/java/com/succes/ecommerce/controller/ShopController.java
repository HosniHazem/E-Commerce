package com.succes.ecommerce.controller;

import com.succes.ecommerce.Repository.OrderRepo;
import com.succes.ecommerce.Repository.ShopRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.model.Order;
import com.succes.ecommerce.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/shop")
public class ShopController {

	@Autowired
	ShopRepo shopRepo;

	@RequestMapping("getAll")
	public List<Shop> getAllShop(){
		return shopRepo.findAll();}
	@GetMapping("/{shopId}")
	public Optional<Shop> findShop(@PathVariable Long shopId) {


		return shopRepo.findById(shopId);

	}
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createShop(@RequestBody Shop shop) {
		shopRepo.save(shop);
		return ResponseEntity.ok(new ApiResponse("Shop created successfully", ""));
	}
	@PutMapping("/update/{shopId}")
	public ResponseEntity<ApiResponse> updateShop(@PathVariable("shopId") Long shopId, @RequestBody Shop shop) throws Exception {

		Optional<Shop> optionalShop = shopRepo.findById(shopId);
		// throw an exception if product does not exists
		if (!optionalShop.isPresent()) {
			throw new Exception("Shop not present");
		}
		Shop shops = optionalShop.get();
		shops.setName(shop.getName());
		shops.setDescription(shop.getDescription());
		shops.setEmail(shop.getEmail());
		shops.setPhoneNumber(shop.getPhoneNumber());
		shops.setAddress(shop.getAddress());
		shops.setProducts(shop.getProducts());
		shops.setUser(shop.getUser());



		shopRepo.save(shops);
		return ResponseEntity.ok(new ApiResponse("Shop Updated successfully", ""));
	}

	@DeleteMapping("/delete/{shopId}")
	public ResponseEntity<ApiResponse> deleteShop(@PathVariable("shopId") Long shopId ){





		shopRepo.deleteById(shopId);

		return ResponseEntity.ok(new ApiResponse(" Shop Deleted successfully", ""));

	}


}
