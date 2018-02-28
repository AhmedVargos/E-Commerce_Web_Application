/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.DAL.models;

/**
 *
 * @author Vargos
 */
public class Order {

    private int order_id;
    private Date order_Date;
    private int user_UserId;

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setOrder_Date(Date order_Date) {
        this.order_Date = order_Date;
    }

    public void setUser_UserId(int user_UserId) {
        this.user_UserId = user_UserId;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Date getOrder_Date() {
        return order_Date;
    }

    public int getUser_UserId() {
        return user_UserId;
    }

}
