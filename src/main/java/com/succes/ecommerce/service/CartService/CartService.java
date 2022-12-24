package com.succes.ecommerce.service.CartService;

import com.succes.ecommerce.model.AddtoCart;
import com.succes.ecommerce.model.CheckoutCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
	List<AddtoCart> addCartbyUserIdAndProductId(long productId,long user_id,int qty,double price) throws Exception;
	void updateQtyByCartId(long id,int qty,double price) throws Exception;
	List<AddtoCart> getCartByUserId(long user_id);
	List<AddtoCart> removeCartByUserId(long id,long user_id);
	List<AddtoCart> removeAllCartByUserId(long user_id);
	Boolean checkTotalAmountAgainstCart(double totalAmount,long user_id);
	List<CheckoutCart> getAllCheckoutByUserId(long user_id);
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;


	//CheckOutCart
}
