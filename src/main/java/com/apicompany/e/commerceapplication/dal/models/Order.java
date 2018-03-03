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
public class Order {

    private int order_id;
    private Date order_Date;
    private User user;
    private ArrayList<Product> products;
    
    public Order() {
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setOrder_Date(Date order_Date) {
        this.order_Date = order_Date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products=products;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Date getOrder_Date() {
        return order_Date;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
