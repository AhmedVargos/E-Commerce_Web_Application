package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.google.gson.Gson;

public class CartPageController {

    public Cart updateUserCart(String json){
        //Get the new cart then replacing it with the old one.
        Cart userCart = new Gson().fromJson(json,Cart.class);

        CartDAO cartDAO = new CartDAO();
        if(cartDAO.replaceOldCartWithNew(userCart.getCartId(),userCart.getCartItems())){
            return userCart;
        }

        return null;
    }

}
