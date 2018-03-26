package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.entities.Cart;

public interface CartDAOInt {
    
    Cart getCartByUserID(int userId);
    Cart getCartByCartID(int CartId);
    Boolean addNewProductToExistingCart(int cartId, int productId,int Quantity);
    Boolean removeCartByUserID(int userId);
    int getProductQuantityInCart(int cartId,int productId);
}
