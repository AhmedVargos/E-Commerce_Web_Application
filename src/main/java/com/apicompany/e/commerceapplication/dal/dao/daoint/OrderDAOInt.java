package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.util.ArrayList;
import java.sql.Date;

public interface OrderDAOInt {
    
     Order getOrderByOrderId(int orderId);
     ArrayList<Order> getOrderByUserId(int userId);
     ArrayList<Order> getOrderByUserName(String userName);
     ArrayList<Order> getOrderByDate(Date date);
     ArrayList<Order> getAllOrders();
     Boolean addNewOrder(User user, ArrayList<Product> products);
     Boolean deleteOrder(int orderId);
     Boolean updateExistingOrder(Order order);
}
