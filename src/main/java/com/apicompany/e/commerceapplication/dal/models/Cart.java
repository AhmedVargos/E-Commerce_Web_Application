/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.models;

import java.util.Date;

/**
 *
 * @author Vargos
 */
public class Cart {

    private int cartId;
    private Date date;
    private int user_userId;

    public Cart() {
    }

    public Cart(int cartId, Date date, int user_userId) {
        this.cartId = cartId;
        this.date = date;
        this.user_userId = user_userId;
    }
   public Cart( Date date, int user_userId) {
        this.date = date;
        this.user_userId = user_userId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser_userId(int user_userId) {
        this.user_userId = user_userId;
    }

    public int getCartId() {
        return cartId;
    }

    public Date getDate() {
        return date;
    }

    public int getUser_userId() {
        return user_userId;
    }

}
