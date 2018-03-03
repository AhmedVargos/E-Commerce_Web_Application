/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.CartDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
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
public class CartDAO implements CartDAOInt {

    DatabaseHandler dbHandler;

    public CartDAO() {
        dbHandler = DatabaseHandler.getDBInstance();
    }

    @Override
    public Cart getUserCart(User user) {
        Cart cart = new Cart();
        ProductDAO pdao = new ProductDAO();
        ArrayList<Product> products = new ArrayList<>();
        int productId;
        PreparedStatement selectStatement;
        ResultSet rs;

        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT cartId, date FROM EcommerceDB.cart WHERE user_userId=" + user.getUserId());
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                cart.setCartId(rs.getInt("cartId"));
                cart.setDate(rs.getDate("date"));

                selectStatement = dbHandler.getCon().prepareStatement("SELECT PC.product_productId "
                        + "FROM EcommerceDB.product_cart PC , EcommerceDB.cart C"
                        + "WHERE C.cartId = PC.cart_cartId"
                        + "AND C.cartId = " + cart.getCartId());
                rs = selectStatement.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("product_productId");
                    if (pdao.getSpecieficProduct(productId) != null) {
                        products.add(pdao.getSpecieficProduct(productId));
                    }

                }
                cart.setCartUser(user);
                cart.setProducts(products);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cart;
    }

    @Override
    public Cart getCartByCartId(int cartId) {
        Cart cart = new Cart();
        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        ArrayList<Product> products = new ArrayList<>();
        int productId;
        PreparedStatement selectStatement;
        ResultSet rs;

        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT user_userId, date FROM EcommerceDB.cart WHERE cartId=" + cartId);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                cart.setCartId(cartId);
                cart.setCartUser(udao.getUser(rs.getInt("user_userId")));
                cart.setDate(rs.getDate("date"));

                selectStatement = dbHandler.getCon().prepareStatement("SELECT PC.product_productId "
                        + "FROM EcommerceDB.product_cart PC , EcommerceDB.cart C"
                        + "WHERE C.cartId = PC.cart_cartId"
                        + "AND C.cartId = " + cartId);
                rs = selectStatement.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("product_productId");
                    if (pdao.getSpecieficProduct(productId) != null) {
                        products.add(pdao.getSpecieficProduct(productId));
                    }

                }
                cart.setProducts(products);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cart;
    }

    @Override
    public ArrayList<Cart> getAllCars() {
        PreparedStatement selectStatement;
        ResultSet rs;
        ArrayList<Cart> carts = new ArrayList<>();
        Cart tempCart;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT cartId FROM EcommerceDB.cart");
            rs = selectStatement.executeQuery();
            while (rs.next()) {
                tempCart = getCartByCartId(rs.getInt("cartId"));
                carts.add(tempCart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    @Override
    public Boolean addNewProductToExistingCart(Cart cart, Product product, int Quantity) {
        ProductDAO productDAO = new ProductDAO();
        PreparedStatement insertStatement;
        Boolean isAdded=false;
        if (productDAO.getSpecieficProduct(product.getProductId()) != null) {
            try {
                insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.product_cart"
                        + " (product_productId, cart_cartId, product_quantity) VALUES(?,?,?)");
                insertStatement.setInt(1,product.getProductId());
                insertStatement.setInt(2,cart.getCartId());
                insertStatement.setInt(3,Quantity);
                insertStatement.executeUpdate();
                isAdded=true;                  
            } catch (SQLException ex) {
                Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isAdded;
    }

    @Override
    public Boolean addNewProductToNewCart(User user, Product product, int Quantity) {
        PreparedStatement insertStatement;
        Boolean isAdded=false;
        Cart cart = new Cart();
        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.cart (date, user_userId)"
                    + "VALUES(?,?)");
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            insertStatement.setDate(1, sqlDate);
            insertStatement.setInt(2, user.getUserId());
            insertStatement.executeUpdate();
            cart= getUserCart(user);
            isAdded= addNewProductToExistingCart(cart, product,Quantity);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdded;
        
    }

    @Override
    public Boolean removeProductFromCart(Cart cart, Product product) {
        PreparedStatement deleteStatement;
        boolean isRemoved=false;
        int cartID = cart.getCartId();
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_cart "
                    + "WHERE cart_cartId =" + cartID
                    + "AND product_productId = "+ product.getProductId());
            deleteStatement.executeUpdate();
            isRemoved = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

    @Override
    public Boolean removeCart(User user) {
        PreparedStatement deleteStatement;
        boolean isRemoved;
        int userID = user.getUserId();
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.cart WHERE userId = ?");
            deleteStatement.setInt(1, userID);
            deleteStatement.executeUpdate();
            isRemoved = true;
        } catch (SQLException ex) {
            isRemoved = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        CartDAO cDao = new CartDAO();
        User user = userDAO.getUser("Gehad");

        // Cart c = cDao.getUserCart(user);
        Cart c = cDao.getCartByCartId(1);
        ArrayList<Cart> carts = cDao.getAllCars();
    }

}
