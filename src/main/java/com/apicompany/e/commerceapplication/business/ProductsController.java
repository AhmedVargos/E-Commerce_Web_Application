/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.ProductDAO;
import com.apicompany.e.commerceapplication.dal.models.Product;

import java.util.ArrayList;

/**
 * @author Vargos
 */
public class ProductsController {
    private ProductDAO productDAO;

    public ProductsController() {
        productDAO = new ProductDAO();
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }

    public Product getProductDetails(int productId) {
        return productDAO.getSpecificProduct(productId);
    }

    public void updateProduct(int productId, int categoryId, String name, String desc, double price, int quantity, String fileName) {
        Product product = new Product(productId, name, desc, fileName, price, quantity, categoryId);
        productDAO.updateProduct(product);
    }


}
