package com.succes.ecommerce.service.ProductService;

import com.succes.ecommerce.Repository.CategoryRepo;
import com.succes.ecommerce.Repository.ProductRepo;
import com.succes.ecommerce.dto.ProductDto;
import com.succes.ecommerce.model.Category;
import com.succes.ecommerce.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public void createProduct(ProductDto productDto, Category category) {
		Products product = new Products();
		product.setAdded_on(productDto.getAdded_on());
		product.setImage_name(productDto.getImage_name());
		product.setName(productDto.getName());
		product.setCategory_id(productDto.getCategory_id());
		product.setPrice(productDto.getPrice());
		productRepo.save(product);
	}
	public void updateProduct(ProductDto productDto, Long productId) throws Exception {
		Optional<Products> optionalProduct = productRepo.findById(productId);
		// throw an exception if product does not exists
		if (!optionalProduct.isPresent()) {
			throw new Exception("product not present");
		}
		Products product = optionalProduct.get();
		product.setAdded_on(productDto.getAdded_on());
		product.setImage_name(productDto.getImage_name());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCategory_id(productDto.getCategory_id());
		productRepo.save(product);
	}
	public void deleteCartItem(Long productId) {
		productRepo.deleteById(productId);
	}
	public void deleteCategoryItem(Long categoryId) {

		cateRepo.deleteById(categoryId);
	}
}
