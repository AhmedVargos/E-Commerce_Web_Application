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
public class Product {

    private int productId;
    private String productName;
    private String description;
    private String image;
    private Double productPrice;
    private int quantity;
    private int catagory_catogeryId;

    public Product() {
    }

    public Product(int productId, String productName, String description, String image, Double productPrice, int quantity, int catagory_catogeryId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.catagory_catogeryId = catagory_catogeryId;
    }

    public Product(String productName, String description, String image, Double productPrice, int quantity) {
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCatagory_catogeryId() {
        return catagory_catogeryId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCatagory_catogeryId(int catagory_catogeryId) {
        this.catagory_catogeryId = catagory_catogeryId;
    }

}
