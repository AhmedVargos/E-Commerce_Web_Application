/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.database.EntityManagerHandler;
import com.apicompany.e.commerceapplication.dal.entities.CartItem;
import com.apicompany.e.commerceapplication.dal.entities.Order1;
import com.apicompany.e.commerceapplication.dal.entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author Vargos
 */
public class OrderDAO  {

//    DatabaseHandler dbHandler;
//
//    public OrderDAO() {
//        dbHandler = DatabaseHandler.getDBInstance();
//    }

    private EntityManagerHandler entityManagerHandler;

    public OrderDAO() {
        entityManagerHandler = EntityManagerHandler.getEntityManagerHandler();
    }

    public Order1 getOrderByOrderId(int orderId) {
        //return getOrderHelper("orderId", orderId);
        
        Order1 order1 = new Order1();
        Query query = entityManagerHandler.getEntityManager().createQuery("select o from Order1 o where o.order1PK.orderId = :orderId");
        query.setParameter("orderId", orderId);
        order1 =  (Order1) query.getSingleResult();
        
//        PreparedStatement selectStatement;
//        ResultSet rs;
//        User currentUser;
//        Product newProduct;
//        ArrayList<Product> productsInOrder;
//        Order order = new Order();
//        try {
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM EcommerceDB.user where userId ="
//                    + " (SELECT user_userId FROM EcommerceDB.order WHERE orderId=" + orderId + ")");
//            rs = selectStatement.executeQuery();
//
//            if (rs.next()) {
//                currentUser = new User();
//                currentUser.setUserId(rs.getInt("userId"));
//                currentUser.setUserName(rs.getString("userName"));
//                currentUser.setBirthdate(rs.getDate("birthdate"));
//                currentUser.setPassWord(rs.getString("password"));
//                currentUser.setEmail(rs.getString("email"));
//                currentUser.setJob(rs.getString("job"));
//                currentUser.setCreditLimit(rs.getInt("creditLimit"));
//                currentUser.setAddress(rs.getString("address"));
//                currentUser.setInterests(rs.getString("interests"));
//                currentUser.setIsAdmin(rs.getBoolean("isAdmin"));
//                order.setUser(currentUser);
//            }
//
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT  O.orderId, O.orderDate, P.*"
//                    + " FROM EcommerceDB.order O JOIN EcommerceDB.product_order P_O"
//                    + " ON O.orderId =  P_O.order_orderId"
//                    + " INNER JOIN EcommerceDB.product P"
//                    + " ON  P.productId = P_O.product_productId"
//                    + " AND O.orderId=" + orderId);
//            rs = selectStatement.executeQuery();
//            productsInOrder = new ArrayList<>();
//
//            if (rs.next()) {
//                order.setOrder_id(rs.getInt("orderId"));
//                order.setOrder_Date(rs.getDate("orderDate"));
//                rs.beforeFirst();
//            }
//            while (rs.next()) {
//                newProduct = new Product();
//                newProduct.setProductId(rs.getInt("productId"));
//                newProduct.setProductName(rs.getString("productName"));
//                newProduct.setDescription(rs.getString("description"));
//                newProduct.setProductPrice(rs.getDouble("productPrice"));
//                newProduct.setImage(rs.getString("image"));
//                newProduct.setQuantity(rs.getInt("quantity"));
//                newProduct.setCatagory_catogeryId(rs.getInt("category_categoryId"));
//                productsInOrder.add(newProduct);
//            }
//            order.setProducts(productsInOrder);
//
//            //  selectStatement =dbHandler.getCon().prepareStatement()
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return order1;

    }
    
    public ArrayList<Order1> getOrderByUserId(int userId) {
//        PreparedStatement selectStatement;
//        ResultSet rs;
//        User currentUser;
//        Product newProduct;
//        ArrayList<Product> productsInOrder;
        ArrayList<Order1> orders = new ArrayList<>();
//        int temp;
//        boolean added = false;
//        Order order;
        
        Order1 order1 = new Order1();
        Query query = entityManagerHandler.getEntityManager().createQuery("select o from Order1 o where o.user.userId = :userId");
        query.setParameter("userId", userId);
        orders =  (ArrayList<Order1>) query.getResultList();
//        try {
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM EcommerceDB.user where userId =" + userId);
//            rs = selectStatement.executeQuery();
//            if (rs.next()) {
//                //get user first
//                currentUser = new User();
//                productsInOrder = new ArrayList<>();
//                orders = new ArrayList<>();
//                order = new Order();
//                currentUser.setUserId(rs.getInt("userId"));
//                currentUser.setUserName(rs.getString("userName"));
//                currentUser.setBirthdate(rs.getDate("birthdate"));
//                currentUser.setPassWord(rs.getString("password"));
//                currentUser.setEmail(rs.getString("email"));
//                currentUser.setJob(rs.getString("job"));
//                currentUser.setCreditLimit(rs.getInt("creditLimit"));
//                currentUser.setAddress(rs.getString("address"));
//                currentUser.setInterests(rs.getString("interests"));
//                currentUser.setIsAdmin(rs.getBoolean("isAdmin"));
//                order.setUser(currentUser);
//
//                // then get orders
//                selectStatement = dbHandler.getCon().prepareStatement("SELECT  O.orderId, O.orderDate, P.*"
//                        + " FROM EcommerceDB.order O JOIN EcommerceDB.product_order P_O"
//                        + " ON O.orderId =  P_O.order_orderId"
//                        + " JOIN EcommerceDB.product P"
//                        + " ON  P.productId = P_O.product_productId"
//                        + " AND O.user_userId=" + currentUser.getUserId()
//                        + " ORDER BY O.orderId");
//
//                rs = selectStatement.executeQuery();
//                if (rs.next()) {
//                    order.setOrder_id(rs.getInt("orderId"));
//                    order.setOrder_Date(rs.getDate("orderDate"));
//                    temp = rs.getInt("orderID");
//                    rs.beforeFirst();
//
//                    while (rs.next()) {
//                        if (temp != rs.getInt("orderID")) {
//                            order.setProducts(productsInOrder);
//                            orders.add(order);
//                            order = new Order();
//                            order.setUser(currentUser);
//                            productsInOrder = new ArrayList<>();
//                            order.setOrder_id(rs.getInt("orderId"));
//                            order.setOrder_Date(rs.getDate("orderDate"));
//                            temp = rs.getInt("orderID");
//                            added = true;
//                        }
//                        newProduct = new Product();
//                        newProduct.setProductId(rs.getInt("productId"));
//                        newProduct.setProductName(rs.getString("productName"));
//                        newProduct.setDescription(rs.getString("description"));
//                        newProduct.setProductPrice(rs.getDouble("productPrice"));
//                        newProduct.setImage(rs.getString("image"));
//                        newProduct.setQuantity(rs.getInt("quantity"));
//                        newProduct.setCatagory_catogeryId(rs.getInt("category_categoryId"));
//                        productsInOrder.add(newProduct);
//                        added = false;
//
//                    }
//                    if (added == false) {
//                        order.setProducts(productsInOrder);
//                        orders.add(order);
//                    }
//                }
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return orders;
    }

   
    public ArrayList<Order1> getOrderByUserName(String name) {
        ArrayList<Order1> orders = new ArrayList<>();
        Order1 order1 = new Order1();
        Query query = entityManagerHandler.getEntityManager().createQuery("select o from Order1 o where o.user.userName = :userName");
        query.setParameter("userName", name);
        orders =  (ArrayList<Order1>) query.getResultList();
//        try {
//            PreparedStatement selectStatement;
//            ResultSet rs;
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT userId FROM EcommerceDB.user where userName ='" + name + "'");
//            rs = selectStatement.executeQuery();
//            if (rs.next()) {
//                orders = getOrderByUserId(rs.getInt("userId"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return orders;
    }

    // not tested yet
    
    public ArrayList<Order1> getOrderByDate(java.sql.Date date) {
        ArrayList<Integer> ordersId = new ArrayList<>();
        ArrayList<Order1> orders = new ArrayList<>();
        ResultSet rs;
        PreparedStatement selectStatement;
        
        Query query = entityManagerHandler.getEntityManager().createQuery("select o from Order1 o where o.orderDate = :date");
        query.setParameter("date", date);
        orders =  (ArrayList<Order1>) query.getResultList();
//        
//        try {
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order where orderDate =" + date);
//            rs = selectStatement.executeQuery();
//            while (rs.next()) {
//                ordersId.add(rs.getInt("orderId"));
//            }
//
//            for (int i = 0; i < ordersId.size(); i++) {
//                if (getOrderByOrderId(ordersId.get(i)) != null) {
//                    orders.add(getOrderByOrderId(ordersId.get(i)));
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return orders;
    }

    
    public ArrayList<Order1> getAllOrders() {
//        ArrayList<Integer> ordersId = new ArrayList<>();
        ArrayList<Order1> orders = new ArrayList<>();
//        ResultSet rs;
//        PreparedStatement selectStatement;
        Query query = entityManagerHandler.getEntityManager().createQuery("select o from Order1 o");
        orders =  (ArrayList<Order1>) query.getResultList();
//        
//        try {
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order");
//            rs = selectStatement.executeQuery();
//            while (rs.next()) {
//                ordersId.add(rs.getInt("orderId"));
//            }
//
//            for (int i = 0; i < ordersId.size(); i++) {
//                if (getOrderByOrderId(ordersId.get(i)) != null) {
//                    orders.add(getOrderByOrderId(ordersId.get(i)));
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return orders;
    }

   
    public Boolean addNewOrder(User user, ArrayList<CartItem> items) {
        Boolean added = false;
        UserDAO userDAO = new UserDAO();
        PreparedStatement insertStatement;
        PreparedStatement insertStatement_2;
        
        

        PreparedStatement selectStatement;
        ResultSet rs;
        int orderId = 0;
        if (userDAO.getUserById(user.getUserId()) == null) {
            userDAO.addUser(user);
        }
        Order1 order1 = new Order1();
        java.util.Date today = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(today.getTime());
        order1.setOrderDate(sqlDate);
        order1.setUser(user);
        entityManagerHandler.getEntityManager().persist(order1);
        entityManagerHandler.getEntityManager().getTransaction().commit();
        
        Query query = entityManagerHandler.getEntityManager().createQuery("select o.order1PK.orderId from Order1 o where o.order1PK.useruserId = :userId");
        query.setParameter("userId", user.getUserId());
        
        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.order (orderDate,user_userId) VALUES (?,?)");
//            java.util.Date today = new java.util.Date();
//            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            insertStatement.setDate(1, sqlDate);
            insertStatement.setInt(2, user.getUserId());
            insertStatement.executeUpdate();
            selectStatement = dbHandler.getCon().prepareStatement("SELECT orderId FROM EcommerceDB.order WHERE order.user_userId = ?");
            selectStatement.setInt(1, user.getUserId());
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                while(rs.next()){
                    orderId = rs.getInt("orderId");
                }

                for (CartItem I : items) {
                    insertStatement_2 = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.product_order (product_productId,order_orderId,product_quantityl) VALUES (?,?,?)");
                    insertStatement_2.setInt(1, I.getProduct().getProductId());
                    insertStatement_2.setInt(2, orderId);
                    insertStatement_2.setInt(3, I.getQuantity());
                    insertStatement_2.executeUpdate();
                }
                added = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }

    
    public Boolean deleteOrder(int orderId) {
        PreparedStatement deleteStatement;
        boolean isRemoved;
        Query query = entityManagerHandler.getEntityManager().createQuery("delete from Order1 o where o.order1PK.orderId = :orderId");
        query.setParameter("orderId", orderId);
        query.executeUpdate();
        isRemoved=true;
//        try {
//            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_order WHERE order_orderId = ?");
//            deleteStatement.setInt(1, orderId);
//            deleteStatement.executeUpdate();
//
//            isRemoved = true;
//        } catch (SQLException ex) {
//            isRemoved = false;
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return isRemoved;
    }

    //Ahmed Edit
    public int getProductQuantityInOrder(int productId,int orderId) {
        PreparedStatement selectStatement;
        ResultSet rs;
        int Quantity = -1;
        
        Query query = entityManagerHandler.getEntityManager().createQuery("select pq.productQuantityl from ProductOrder pq where pq.productOrderPK.productproductId = :productId and pq.productOrderPK.orderorderId= :orderId");
        query.setParameter("productId", productId);
        query.setParameter("orderId", orderId);
        Quantity = (int) query.getSingleResult(); 
//        
//        try {
//            selectStatement = dbHandler.getCon().prepareStatement("SELECT product_quantityl"
//                    + " FROM EcommerceDB.product_order"
//                    + " WHERE product_productId = ? AND order_orderId = ?");
//            selectStatement.setInt(1, productId);
//            selectStatement.setInt(2, orderId);
//            rs = selectStatement.executeQuery();
//            if (rs.next()) {
//                Quantity = rs.getInt("product_quantityl");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return Quantity;
    }

    /*@Override
    public Boolean updateExistingOrder(Order order) {
        
        int orderId = order.getOrder_id();
        ArrayList<Product> products = order.getProducts();
        User currentUser = order.getUser();
        deleteOrder(orderId);
        return addNewOrder(currentUser, products.);
    }*/
//    public static void main(String[] args) {
//        OrderDAO odao = new OrderDAO();
//        ArrayList<Order> orders = new ArrayList<>();
//        orders = odao.getAllOrders();
//    }
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
