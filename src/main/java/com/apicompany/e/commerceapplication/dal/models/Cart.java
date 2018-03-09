/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.models;

import java.sql.Date;
import java.util.ArrayList;
//import java.util.Date;

/**
 *
 * @author Vargos
 */
public class Cart {

    private int cartId;
    private Date date;
    private User cartUser;
    private ArrayList<CartItem> cartItems;

    public Cart() {
    }

    public Cart(int cartId, Date date) {
        this.cartId = cartId;
        this.date = date;
    }
   public Cart( Date date, int user_userId) {
        this.date = date;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCartUser(User cartUser) {
        this.cartUser = cartUser;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getCartId() {
        return cartId;
    }

    public Date getDate() {
        return date;
    }

    public User getCartUser() {
        return cartUser;
    }
    
}
