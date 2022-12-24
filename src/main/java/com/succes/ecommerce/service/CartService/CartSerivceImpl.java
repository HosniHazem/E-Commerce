package com.succes.ecommerce.service.CartService;

import com.succes.ecommerce.Repository.AddToCartRepo;
import com.succes.ecommerce.Repository.CheckoutRepo;
import com.succes.ecommerce.model.AddtoCart;
import com.succes.ecommerce.model.CheckoutCart;
import com.succes.ecommerce.model.Products;
import com.succes.ecommerce.service.ProductService.ProductServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSerivceImpl implements CartService {

	@Autowired
	AddToCartRepo addCartRepo;
	@Autowired
	CheckoutRepo checkOutRepo;
	@Autowired
	ProductServices proServices;
	private static final Logger logger = LoggerFactory.getLogger(CartSerivceImpl.class);

	@Override
	public List<AddtoCart> addCartbyUserIdAndProductId(long productId, long user_id,int qty,double price) throws Exception {
		try {
			if(addCartRepo.getCartByProductIdAnduserId(user_id, productId).isPresent()){
				throw new Exception("Product is already exist.");
			}
			AddtoCart obj = new AddtoCart();
			obj.setQty(qty);
			obj.setUser_id(user_id);
			Products pro = proServices.getProductsById(productId);
			obj.setProduct(pro);
			//TODO price has to check with qty
			obj.setPrice(price);
			addCartRepo.save(obj);
			return this.getCartByUserId(user_id);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<AddtoCart> getCartByUserId(long user_id) {
		return addCartRepo.getCartByuserId(user_id);
	}

	@Override
	public List<AddtoCart> removeCartByUserId(long id, long user_id) {
		addCartRepo.deleteCartByIdAndUserId(user_id, id);
		return this.getCartByUserId(user_id);
	}

	@Override
	public void updateQtyByCartId(long id, int qty, double price) throws Exception {
		addCartRepo.updateQtyByCartId(id,price,qty);
	}

	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount,long user_id) {
		double total_amount =addCartRepo.getTotalAmountByUserId(user_id);
		if(total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
		return false;
	}

	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long user_id) {
		return checkOutRepo.getByuserId(user_id);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUser_id();
			if(tmp.size() >0) {
				checkOutRepo.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			}
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}

	}

	@Override
	public List<AddtoCart> removeAllCartByUserId(long user_id) {
		addCartRepo.deleteAllCartByUserId(user_id);
		return null;
	}

}
