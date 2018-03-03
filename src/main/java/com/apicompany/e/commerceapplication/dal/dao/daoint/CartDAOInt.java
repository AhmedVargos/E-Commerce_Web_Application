package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.util.ArrayList;

public interface CartDAOInt {
    
    Cart getUserCart(User user);
    Cart getCartByCartId(int CartId);
    ArrayList<Cart> getAllCars();
    Boolean addNewProductToExistingCart(Cart cart, Product product,int Quantity);
    Boolean addNewProductToNewCart(User user, Product product,int Quantity);
    Boolean removeProductFromCart(Cart cart, Product product);
    Boolean removeCart(User user);
}
