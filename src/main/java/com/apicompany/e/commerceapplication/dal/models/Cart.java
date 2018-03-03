/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vargos
 */
public class Cart {

    private int cartId;
    private Date date;
    private User cartUser;
    private ArrayList<Product> products;

    public Cart() {
    }

    public Cart(int cartId, Date date, int user_userId) {
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

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
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
