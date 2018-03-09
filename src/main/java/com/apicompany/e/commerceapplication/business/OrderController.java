/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.OrderDAO;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vargos
 */
public class OrderController {
    
    OrderDAO orderDAO;

    public OrderController() {
        orderDAO=new OrderDAO();
    }
    
    public List<Order> getAllOrders(){   
        return orderDAO.getAllOrders();
    }
    
    public List<Order> calculateOrderPrice(){
        List<Order> orders = getAllOrders();
        double sum=0;
        List<Product> products = new ArrayList<>();
        for(int i=0; i< orders.size(); i++){
            sum=0;
            products.clear();
            products= orders.get(i).getProducts();
            for (Product product : products) {
                sum+= product.getProductPrice();
            }
            orders.get(i).setTotalPrice(sum);
        }
        return orders;
    }
}
