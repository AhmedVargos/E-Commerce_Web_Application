/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.OrderDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vargos
 */
public class OrderDAO implements OrderDAOInt {

    DatabaseHandler dbHandler;

    public OrderDAO() {
        dbHandler = DatabaseHandler.getDBInstance();
    }

    @Override
    public void getOrderByOrderId(int orderId) {
        PreparedStatement selectStatement;
                PreparedStatement test;

        ResultSet rs;
        ResultSet test_;
        Order order = new Order();
        try {   
            test= dbHandler.getCon().prepareStatement("SELECT user_userID FROM ORDER WHERE orderId = " + orderId);
            test_=test.executeQuery();
            int t= test_.getInt(1);
            
            selectStatement = dbHandler.getCon().prepareStatement("SELECT O.orderId, O.orderDate, P.* "
                    + " FROM ORDER inner join PRODUCT_ORDER PO ON O.orderId = PO.orderId "
                    + " inner join PRODUCT P ON PO.productId = P.productId WHERE O.OrderId = " + orderId);
            
          
            
/*select con.name as contact_name , com.name as company_name,campa.name as campaign_name
from contact con inner join company com
on con.companyid = com.companyid
inner join campaign campa
on com.campaignid = campa.campaignid*/
            rs = selectStatement.executeQuery();
            order.setOrder_id(rs.getInt(1));
            order.setOrder_Date(rs.getDate(2));
            order.setUser((User) rs.getObject(3));
            order.setProducts((ArrayList)(rs.getArray(4)));

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        o.getOrderByOrderId(1);
    }

}
//com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 
//'ORDER O, USER U, PRODUCT P, PRODUCT_ORDER P_OWHERE O.OrderId = 1AND O.user_userI' at line 1