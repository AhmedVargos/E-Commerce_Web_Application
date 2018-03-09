/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.ProductDAO;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Product;

import java.util.ArrayList;

/**
 * @author Vargos
 */
public class CartController {

    Product product;
    private ProductDAO productDAO;
    private CartDAO cartDAO;
    Cart cart = new Cart();
    CartItem cartItem;
    ArrayList<CartItem> cartList = new ArrayList<>();

    boolean isNewCartCreated;

    public boolean isNewCartCreated() {
        return isNewCartCreated;
    }

    private void setNewCartCreated(boolean newCartCreated) {
        isNewCartCreated = newCartCreated;
    }

    public CartController() {
        productDAO = new ProductDAO();
        cartDAO = new CartDAO();
    }

    public Cart addToCart(int id, int quantity) {

        product = productDAO.getSpecificProduct(id);
        cartItem = new CartItem(quantity, product);
        cartList.add(cartItem);
        cart.setCartItems(cartList);
        return cart;
    }

    public Cart appendToCart(int id, int quantity, Cart oldCart) {
        cartList = oldCart.getCartItems();
        int resQ = quantity;
        boolean isFound = false;
        //check product is exist in old cart if yes increase quantity
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getProduct().getProductId() == id) {
                int oldQuantity = cartList.get(i).getQuantity();
                resQ += oldQuantity;
                cartList.get(i).setQuantity(resQ);
                //newCartItem.setQuantity(resQ);
                //cartItem.setQuantity(oldQuantity);
                isFound = true;
            }
        }/*
        for (CartItem cartItem : cartList) {
            if (cartItem.getProduct().getProductId() == id) {
                int oldQuantity = cartItem.getQuantity();
                resQ += oldQuantity;
                newCartItem.setQuantity(resQ);
                //cartItem.setQuantity(oldQuantity);
                isFound = true;
            }
        }*/
        if (!isFound) {
            CartItem newCartItem = new CartItem();
            product = productDAO.getSpecificProduct(id);
            newCartItem.setQuantity(resQ);
            newCartItem.setProduct(product);
            cartList.add(newCartItem);
        }

//        cartItem = new CartItem(quantity,product);
        //cartList.add(cartItem);

        oldCart.setCartItems(cartList);
        return oldCart;
    }

    public void createNewCart(int userId) {
        boolean cartCreated = cartDAO.createEmptyCart(userId);

        if (cartCreated) {
            setNewCartCreated(true);
        }
    }

    public void addProductsToCart(Cart cart) {
        ArrayList<CartItem> cartItems = cart.getCartItems();

        cartDAO.addExistingCartItems(cart.getCartId(), cartItems);
    }

    public Cart getCurrentCart(int userId) {
        Cart cart = cartDAO.getCartByUserID(userId);
        return cart;
    }
}
