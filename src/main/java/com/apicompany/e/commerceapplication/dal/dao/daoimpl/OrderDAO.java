/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.OrderDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public Order getOrderByOrderId(int orderId) {
        //return getOrderHelper("orderId", orderId);
        PreparedStatement selectStatement;
        ResultSet rs;
        User currentUser;
        Product newProduct;
        ArrayList<Product> productsInOrder;
        Order order = new Order();
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM EcommerceDB.user where userId ="
                    + " (SELECT user_userId FROM EcommerceDB.order WHERE orderId=" + orderId + ")");
            rs = selectStatement.executeQuery();

            if (rs.next()) {
                currentUser = new User();
                currentUser.setUserId(rs.getInt("userId"));
                currentUser.setUserName(rs.getString("userName"));
                currentUser.setBirthdate(rs.getDate("birthdate"));
                currentUser.setPassWord(rs.getString("password"));
                currentUser.setEmail(rs.getString("email"));
                currentUser.setJob(rs.getString("job"));
                currentUser.setCreditLimit(rs.getInt("creditLimit"));
                currentUser.setAddress(rs.getString("address"));
                currentUser.setInterests(rs.getString("interests"));
                currentUser.setIsAdmin(rs.getBoolean("isAdmin"));
                order.setUser(currentUser);
            }

            selectStatement = dbHandler.getCon().prepareStatement("SELECT  O.orderId, O.orderDate, P.*"
                    + " FROM EcommerceDB.order O JOIN EcommerceDB.product_order P_O"
                    + " ON O.orderId =  P_O.order_orderId"
                    + " INNER JOIN EcommerceDB.product P"
                    + " ON  P.productId = P_O.product_productId"
                    + " AND O.orderId=" + orderId);
            rs = selectStatement.executeQuery();
            productsInOrder = new ArrayList<>();

            if (rs.next()) {
                order.setOrder_id(rs.getInt("orderId"));
                order.setOrder_Date(rs.getDate("orderDate"));
                rs.beforeFirst();
            }
            while (rs.next()) {
                newProduct = new Product();
                newProduct.setProductId(rs.getInt("productId"));
                newProduct.setProductName(rs.getString("productName"));
                newProduct.setDescription(rs.getString("description"));
                newProduct.setProductPrice(rs.getDouble("productPrice"));
                newProduct.setImage(rs.getString("image"));
                newProduct.setQuantity(rs.getInt("quantity"));
                newProduct.setCatagory_catogeryId(rs.getInt("category_categoryId"));
                productsInOrder.add(newProduct);
            }
            order.setProducts(productsInOrder);

            //  selectStatement =dbHandler.getCon().prepareStatement()
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;

    }

    @Override
    public ArrayList<Order> getOrderByUserId(int userId) {
        PreparedStatement selectStatement;
        ResultSet rs;
        User currentUser;
        Product newProduct;
        ArrayList<Product> productsInOrder;
        ArrayList<Order> orders = new ArrayList<>();
        int temp;
        boolean added = false;
        Order order;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM EcommerceDB.user where userId =" + userId);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                //get user first
                currentUser = new User();
                productsInOrder = new ArrayList<>();
                orders = new ArrayList<>();
                order = new Order();
                currentUser.setUserId(rs.getInt("userId"));
                currentUser.setUserName(rs.getString("userName"));
                currentUser.setBirthdate(rs.getDate("birthdate"));
                currentUser.setPassWord(rs.getString("password"));
                currentUser.setEmail(rs.getString("email"));
                currentUser.setJob(rs.getString("job"));
                currentUser.setCreditLimit(rs.getInt("creditLimit"));
                currentUser.setAddress(rs.getString("address"));
                currentUser.setInterests(rs.getString("interests"));
                currentUser.setIsAdmin(rs.getBoolean("isAdmin"));
                order.setUser(currentUser);

                // then get orders
                selectStatement = dbHandler.getCon().prepareStatement("SELECT  O.orderId, O.orderDate, P.*"
                        + " FROM EcommerceDB.order O JOIN EcommerceDB.product_order P_O"
                        + " ON O.orderId =  P_O.order_orderId"
                        + " JOIN EcommerceDB.product P"
                        + " ON  P.productId = P_O.product_productId"
                        + " AND O.user_userId=" + currentUser.getUserId()
                        + " ORDER BY O.orderId");

                rs = selectStatement.executeQuery();
                if (rs.next()) {
                    order.setOrder_id(rs.getInt("orderId"));
                    order.setOrder_Date(rs.getDate("orderDate"));
                    temp = rs.getInt("orderID");
                    rs.beforeFirst();

                    while (rs.next()) {
                        if (temp != rs.getInt("orderID")) {
                            order.setProducts(productsInOrder);
                            orders.add(order);
                            order = new Order();
                            order.setUser(currentUser);
                            productsInOrder = new ArrayList<>();
                            order.setOrder_id(rs.getInt("orderId"));
                            order.setOrder_Date(rs.getDate("orderDate"));
                            temp = rs.getInt("orderID");
                            added = true;
                        }
                        newProduct = new Product();
                        newProduct.setProductId(rs.getInt("productId"));
                        newProduct.setProductName(rs.getString("productName"));
                        newProduct.setDescription(rs.getString("description"));
                        newProduct.setProductPrice(rs.getDouble("productPrice"));
                        newProduct.setImage(rs.getString("image"));
                        newProduct.setQuantity(rs.getInt("quantity"));
                        newProduct.setCatagory_catogeryId(rs.getInt("category_categoryId"));
                        productsInOrder.add(newProduct);
                        added = false;

                    }
                    if (added == false) {
                        order.setProducts(productsInOrder);
                        orders.add(order);
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;
    }

    @Override
    public ArrayList<Order> getOrderByUserName(String name) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement selectStatement;
            ResultSet rs;
            selectStatement = dbHandler.getCon().prepareStatement("SELECT userId FROM EcommerceDB.user where userName ='" + name + "'");
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                orders = getOrderByUserId(rs.getInt("userId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    // not tested yet
    @Override
    public ArrayList<Order> getOrderByDate(java.sql.Date date) {
        ArrayList<Integer> ordersId = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs;
        PreparedStatement selectStatement;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order where orderDate =" + date);
            rs = selectStatement.executeQuery();
            while (rs.next()) {
                ordersId.add(rs.getInt("orderId"));
            }

            for (int i = 0; i < ordersId.size(); i++) {
                if (getOrderByOrderId(ordersId.get(i)) != null) {
                    orders.add(getOrderByOrderId(ordersId.get(i)));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Integer> ordersId = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs;
        PreparedStatement selectStatement;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order");
            rs = selectStatement.executeQuery();
            while (rs.next()) {
                ordersId.add(rs.getInt("orderId"));
            }

            for (int i = 0; i < ordersId.size(); i++) {
                if (getOrderByOrderId(ordersId.get(i)) != null) {
                    orders.add(getOrderByOrderId(ordersId.get(i)));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    
    @Override
    public Boolean addNewOrder(User user, ArrayList<CartItem> items) {
        Boolean added = false;
        UserDAO userDAO = new UserDAO();
        PreparedStatement insertStatement;
        PreparedStatement insertStatement_2;

        PreparedStatement selectStatement;
        ResultSet rs;
        int orderId;
        if (userDAO.getUserById(user.getUserId()) == null) {
            userDAO.addUser(user);
        }

        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.order (orderDate,user_userId) VALUES (?,?)");
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            insertStatement.setDate(1, sqlDate);
            insertStatement.setInt(2, user.getUserId());
            insertStatement.executeUpdate();
            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order WHERE order.user_userId = ?");
            selectStatement.setInt(1, user.getUserId());
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("orderId");

                for (CartItem I : items) {
                    insertStatement_2 = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.product_order (product_productId,order_orderId,product_quantityl) VALUES (?,?,?)");                  
                    insertStatement_2.setInt(1, I.getProduct().getProductId());
                    insertStatement_2.setInt(2, orderId);
                    insertStatement_2.setInt(3, I.getQuantity());
                    insertStatement_2.executeUpdate();
                }
            }
            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }

    @Override
    public Boolean deleteOrder(int orderId) {
        PreparedStatement deleteStatement;
        boolean isRemoved;
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_order WHERE order_orderId = ?");
            deleteStatement.setInt(1, orderId);
            deleteStatement.executeUpdate();

            isRemoved = true;
        } catch (SQLException ex) {
            isRemoved = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }
    
    /*@Override
    public Boolean updateExistingOrder(Order order) {
        
        int orderId = order.getOrder_id();
        ArrayList<Product> products = order.getProducts();
        User currentUser = order.getUser();
        deleteOrder(orderId);
        return addNewOrder(currentUser, products.);
    }*/

    public static void main(String[] args) {
        OrderDAO odao = new OrderDAO();
        ArrayList<Order> orders = new ArrayList<>();
        orders = odao.getAllOrders();
    }
 /*  public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
//    o.getOrderByOrderId(1); 
//     o.getOrderByUserId(1);
//    String name = "Gehad";
//    o.getOrderByUserName(name);
//    ArrayList<Order> temp = new ArrayList<>();
//    temp = o.getAllOrders();
//    o.deleteOrder(19);

     UserDAO udao = new UserDAO();
        User user = udao.getUser(1);
        Product p = new Product();
        ArrayList<Product> ps = new ArrayList<>();
        p.setProductName("jeans");
        p.setProductPrice(100.0);
        p.setQuantity(12);
        p.setCatagory_catogeryId(1);
        p.setDescription("jeans for women kkkkk");
        p.setImage("aaaaaa");
        ps.add(p);
        ProductDAO pDao = new ProductDAO();
        pDao.insertProduct(p);
        o.addNewOrder(user, ps);
}*/ 
}
