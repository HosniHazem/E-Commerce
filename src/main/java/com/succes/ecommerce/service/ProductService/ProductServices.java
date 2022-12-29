package com.succes.ecommerce.service.ProductService;

import java.util.List;
import java.util.Optional;

import com.succes.ecommerce.controller.RequestPojo.ApiResponse;
import com.succes.ecommerce.dto.CategoryDto;
import com.succes.ecommerce.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.succes.ecommerce.Repository.CategoryRepo;
import com.succes.ecommerce.Repository.ProductRepo;
import com.succes.ecommerce.model.Category;
import com.succes.ecommerce.model.Products;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductServices {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	CategoryRepo cateRepo;

	public List<Products>getAllProducts(){
		return productRepo.findAll();
	}
	public List<Products>getProductsByCategory(String product_id){

		return productRepo.getByCategoryId(product_id);
	}

	public List<Category>getAllCategory(){
		return cateRepo.findAll();
	}

	public Optional<Products> findProduct(Long productId) {
		return productRepo.findById(productId);
	}
	public Optional<Category> findCategory(Long categoryId) {
		return cateRepo.findById(categoryId);
	}
	public Products getProductsById(long productId) throws Exception {
		return productRepo.findById(productId).orElseThrow(() ->new Exception("Product is not found"));
	}
	public void createCategory(Category category) {
		cateRepo.save(category);
	}

	public void createProduct(Products product, Category category) {

		productRepo.save(product);
	}
	public void updateProduct(Products product, Long productId) throws Exception {
		Optional<Products> optionalProduct = productRepo.findById(productId);
		// throw an exception if product does not exists
		if (!optionalProduct.isPresent()) {
			throw new Exception("product not present");
		}
		Products produc = optionalProduct.get();
		produc.setAdded_on(product.getAdded_on());
		produc.setImages(product.getImages());
		produc.setName(product.getName());
		produc.setPrice(product.getPrice());
		produc.setDiscount(product.getDiscount());
		produc.setDescription(product.getDescription());
		produc.setQuantity(product.getQuantity());
		produc.setShop(product.getShop());
		produc.setOrders(product.getOrders());
		produc.setCategory_id(product.getCategory_id());
		productRepo.save(produc);
	}
	public void deleteCartItem(Long productId) {
		productRepo.deleteById(productId);
	}
	public void deleteCategoryItem(Long categoryId) {

		cateRepo.deleteById(categoryId);
	}
}
