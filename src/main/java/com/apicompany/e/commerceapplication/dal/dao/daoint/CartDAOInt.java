package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import java.util.ArrayList;

public interface CartDAOInt {
    
    Cart getCartByUserID(int userId);
    Cart getCartByCartID(int CartId);
    ArrayList<Cart> getAllCars();
    Boolean addNewProductToExistingCart(int cartId, int productId,int Quantity);
    Boolean addNewProductToNewCart(int userId, int productId,int Quantity);
    Boolean removeProductFromCart(int cartId, int productId);
    Boolean removeCartByUserID(int userId);
    Boolean removeCartByCartID(int cartId);
    Boolean updateExistingCart(int cartId,ArrayList<CartItem> updatedItems);
    int getProductQuantityInCart(int cartId,int productId);
    Boolean addEmptyCart(Cart cart);
    Boolean isCartExist(int userId);
}
