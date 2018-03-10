/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.models;
/**
 *
 * @author body
 */
public class CheckoutModel {
  int totalPrice;
  String userCurrent;
    String userAddress;
    public int getTotalPrice() {
        return totalPrice;
    }

    public String getUserCurrent() {
        return userCurrent;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserCurrent(String userCurrent) {
        this.userCurrent = userCurrent;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
 
}
