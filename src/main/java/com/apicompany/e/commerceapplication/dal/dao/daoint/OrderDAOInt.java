package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.entities.CartItem;
import com.apicompany.e.commerceapplication.dal.entities.Order1;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.util.ArrayList;
import java.sql.Date;

public interface OrderDAOInt {
    
    public Order1 getOrder1ByOrder1Id(int order1Id);
    public ArrayList<Order1> getOrder1ByUserId(int userId);
    public ArrayList<Order1> getOrder1ByUserName(String userName);
    public ArrayList<Order1> getOrder1ByDate(Date date);
    public ArrayList<Order1> getAllOrder1s();
    public Boolean addNewOrder1(User user, ArrayList<CartItem> items);
    public Boolean deleteOrder1(int order1Id);
    int getProductQuantityInOrder1(int productId,int order1Id);
    // Boolean updateExistingOrder1(Order1 order1);
}
