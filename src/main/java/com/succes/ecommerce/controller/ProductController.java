package com.succes.ecommerce.controller;

import com.succes.ecommerce.Repository.CategoryRepo;
import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.dto.ProductDto;
import com.succes.ecommerce.model.Category;
import com.succes.ecommerce.model.Products;
import com.succes.ecommerce.service.ProductService.ProductServices;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
public class ProductController {
	@Autowired
	ProductServices ProductServices;

	@Autowired
	private CategoryRepo categoryRepo;

	@RequestMapping("getAll")
	public List<Products> getAllPRoducts(){
		return ProductServices.getAllProducts();
	}
	@RequestMapping("getAllCategory")
	public List<Category> getAllCategory(){
		return ProductServices.getAllCategory();
	}
	@RequestMapping("getProductsByCategory/{categoryId}")
	public List<Products> getProductsByCategory(@RequestBody HashMap<String,String> request){
		String category_id = request.get("cat_id");
		return ProductServices.getProductsByCategory(category_id);
	}
	@GetMapping("/{productId}")
	public Optional<Products> findProduct(@PathVariable Long productId) {


		return ProductServices.findProduct(productId);

	}
	@GetMapping("/category/{categoryId}")
	public Optional<Category> findCategory(@PathVariable Long categoryId) {


		return ProductServices.findCategory(categoryId);

	}
	
	
	@GetMapping( value = "/getimage/{img_name}",produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
	    InputStream in = getClass().getResourceAsStream("/images/"+img_name);
	    return IOUtils.toByteArray(in);
	}
	@PostMapping("/category")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		ProductServices.createCategory(category);
		return ResponseEntity.ok(new ApiResponse("Category successfully", ""));
	}
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategory_id());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new ApiResponse("category does not exists", ""));
		}
		ProductServices.createProduct(productDto, optionalCategory.get());
		return ResponseEntity.ok(new ApiResponse(" Product Created successfully", ""));
	}
	@PutMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws Exception {
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategory_id());
		if (!optionalCategory.isPresent()) {
			return ResponseEntity.badRequest().body(new ApiResponse("category does not exists", ""));
		}
		ProductServices.updateProduct(productDto, productId);
		return ResponseEntity.ok(new ApiResponse(" Product Updated successfully", ""));
	}
	@PutMapping("/updateCat/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category newcategory ) throws Exception {

	 return categoryRepo.findById(categoryId)
			.map(category -> {
				category.setName(newcategory.getName());
		return categoryRepo.save(category);
	})
			.orElseGet(() -> {
				newcategory.setId(categoryId);
		return categoryRepo.save(newcategory);
	});

}
	@DeleteMapping("/deleteProd/{productId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("productId") Long productId ){





		ProductServices.deleteCartItem(productId);

		return ResponseEntity.ok(new ApiResponse(" Product Deleted successfully", ""));

	}
	@DeleteMapping("/deleteCat/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategoryItem(@PathVariable("categoryId") Long categoryId ){





		ProductServices.deleteCategoryItem(categoryId);

		return ResponseEntity.ok(new ApiResponse(" Category Deleted successfully", ""));

	}


}
