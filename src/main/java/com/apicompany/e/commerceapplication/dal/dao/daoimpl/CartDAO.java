/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.CartDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
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

    //tested
    @Override
    public Cart getCartByUserID(int userId) {
        Cart cart = new Cart();
        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        ArrayList<CartItem> cartItems = new ArrayList<>();
        int productId;
        int productQuantity;
        PreparedStatement selectStatement;
        ResultSet rs;

        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT cartId, date FROM EcommerceDB.cart WHERE user_userId=" + userId);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                cart.setCartId(rs.getInt("cartId"));
                cart.setDate(rs.getDate("date"));

                selectStatement = dbHandler.getCon().prepareStatement("SELECT PC.product_productId, PC.product_quantity"
                        + " FROM EcommerceDB.product_cart PC , EcommerceDB.cart C"
                        + " WHERE C.cartId = PC.cart_cartId"
                        + " AND C.cartId = " + cart.getCartId());
                rs = selectStatement.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("product_productId");
                    productQuantity = rs.getInt("product_quantity");

                    if (pdao.getSpecificProduct(productId) != null) {
                        cartItems.add(new CartItem(productQuantity, pdao.getSpecificProduct(productId)));
                    }

                }
                cart.setCartUser(udao.getUserById(userId));
                cart.setCartItems(cartItems);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cart;
    }

    //tested
    @Override
    public Cart getCartByCartID(int cartId) {
        Cart cart = new Cart();
        ProductDAO pdao = new ProductDAO();
        UserDAO udao = new UserDAO();
        ArrayList<CartItem> cartItems = new ArrayList<>();
        int productId;
        int productQuantity;

        PreparedStatement selectStatement;
        ResultSet rs;

        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT user_userId, date FROM EcommerceDB.cart WHERE cartId=" + cartId);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                cart.setCartId(cartId);
                cart.setCartUser(udao.getUserById(rs.getInt("user_userId")));
                cart.setDate(rs.getDate("date"));

                selectStatement = dbHandler.getCon().prepareStatement("SELECT PC.product_productId , PC.product_quantity"
                        + " FROM EcommerceDB.product_cart PC , EcommerceDB.cart C"
                        + " WHERE C.cartId = PC.cart_cartId"
                        + " AND C.cartId = " + cartId);
                rs = selectStatement.executeQuery();
                while (rs.next()) {
                    productId = rs.getInt("product_productId");
                    productQuantity = rs.getInt("product_quantity");

                    if (pdao.getSpecificProduct(productId) != null) {
                        cartItems.add(new CartItem(productQuantity, pdao.getSpecificProduct(productId)));
                    }

                }
                cart.setCartItems(cartItems);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cart;
    }

    //tested
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
                tempCart = getCartByCartID(rs.getInt("cartId"));
                carts.add(tempCart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

//---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    @Override
    public Boolean addNewProductToExistingCart(int cartId, int productId, int Quantity) {
        ProductDAO productDAO = new ProductDAO();
        PreparedStatement insertStatement;
        Boolean isAdded = false;
        if (productDAO.getSpecificProduct(productId) != null) {
            try {
                insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.product_cart"
                        + " (product_productId, cart_cartId, product_quantity) VALUES(?,?,?)");
                insertStatement.setInt(1, productId);
                insertStatement.setInt(2, cartId);
                insertStatement.setInt(3, Quantity);
                insertStatement.executeUpdate();
                isAdded = true;
            } catch (SQLException ex) {
                Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isAdded;
    }

    //tested
    @Override
    public Boolean addNewProductToNewCart(int userId, int productId, int Quantity) {
        PreparedStatement insertStatement;
        Boolean isAdded = false;
        Cart cart = new Cart();
        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.cart (date, user_userId)"
                    + "VALUES(?,?)");
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            insertStatement.setDate(1, sqlDate);
            insertStatement.setInt(2, userId);
            insertStatement.executeUpdate();
            cart = getCartByUserID(userId);
            isAdded = addNewProductToExistingCart(cart.getCartId(), productId, Quantity);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdded;

    }

//---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    @Override
    public Boolean removeProductFromCart(int cartId, int productId) {
        PreparedStatement deleteStatement;
        boolean isRemoved = false;
        int cartID = cartId;
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_cart "
                    + " WHERE cart_cartId =" + cartID
                    + " AND product_productId = " + productId);
            deleteStatement.executeUpdate();
            isRemoved = true;

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

    //tested
    @Override
    public Boolean removeCartByUserID(int userId) {
        Cart cart = new Cart();
        PreparedStatement deleteStatement;
        boolean isRemoved = false;
        try {
            cart = getCartByUserID(userId);
            if (cart != null) {
                deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_cart WHERE cart_cartId = ?");
                deleteStatement.setInt(1, cart.getCartId());
                deleteStatement.executeUpdate();

                deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.cart WHERE userId = ?");
                deleteStatement.setInt(1, userId);
                deleteStatement.executeUpdate();
                isRemoved = true;
            }
        } catch (SQLException ex) {
            isRemoved = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

    //tested
    @Override
    public Boolean removeCartByCartID(int cartId) {
        PreparedStatement deleteStatement;
        boolean isRemoved;
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.product_cart WHERE cart_cartId = ?");
            deleteStatement.setInt(1, cartId);
            deleteStatement.executeUpdate();

            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM EcommerceDB.cart WHERE cartId = ?");
            deleteStatement.setInt(1, cartId);
            deleteStatement.executeUpdate();
            isRemoved = true;
        } catch (SQLException ex) {
            isRemoved = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

//---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    @Override
    public Boolean updateExistingCart(int cartId, ArrayList<CartItem> updatedItems) {
        PreparedStatement updateStatement;
        boolean isUpdated = false;
        Cart tempCart = getCartByCartID(cartId);
        if (tempCart != null) {
            for (CartItem updatedItem : updatedItems) {
                try {
                    if (isProductExistInCart(cartId, updatedItem.getProduct().getProductId())) {
                        updateStatement = dbHandler.getCon().prepareStatement("UPDATE EcommerceDB.product_cart"
                                + " SET product_quantity = ?"
                                + " WHERE cart_cartId = ?"
                                + " AND product_productId = ?");
                        updateStatement.setInt(1, updatedItem.getQuantity());
                        updateStatement.setInt(2, cartId);
                        updateStatement.setInt(3, updatedItem.getProduct().getProductId());
                        updateStatement.executeUpdate();
                        isUpdated = true;
                    } else {
                        addNewProductToExistingCart(cartId, updatedItem.getProduct().getProductId(), updatedItem.getQuantity());
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return isUpdated;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    @Override
    public int getProductQuantityInCart(int cartId, int productId) {
        PreparedStatement selectStatement;
        ResultSet rs;
        int Quantity = -1;
        if (isProductExistInCart(cartId, productId)) {
            try {
                selectStatement = dbHandler.getCon().prepareStatement("SELECT product_quantity"
                        + " FROM EcommerceDB.product_cart"
                        + " WHERE cart_cartId = ?"
                        + " AND product_productId = ?");
                selectStatement.setInt(1, cartId);
                selectStatement.setInt(2, productId);
                rs = selectStatement.executeQuery();
                if (rs.next()) {
                    Quantity = rs.getInt("product_quantity");
                }

            } catch (SQLException ex) {
                Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Quantity;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    
//tested
    Boolean isProductExistInCart(int cartId, int productId) {
        PreparedStatement selectProduct;
        ResultSet rs;
        boolean productExist;
        try {
            selectProduct = dbHandler.getCon().prepareStatement("SELECT * FROM  EcommerceDB.product_cart"
                    + " WHERE cart_cartId = ?"
                    + " AND product_productId = ?");
            selectProduct.setInt(1, cartId);
            selectProduct.setInt(2, productId);
            rs = selectProduct.executeQuery();
            productExist = rs.next();

        } catch (SQLException ex) {
            productExist = false;
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productExist;

    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    @Override
    public Boolean addEmptyCart(Cart cart) {
        PreparedStatement insertStatement;
        Boolean isAdded = false;

        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO EcommerceDB.cart (date, user_userId)"
                    + "VALUES(?,?)");
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            insertStatement.setDate(1, sqlDate);
            insertStatement.setInt(2, cart.getCartUser().getUserId());
            insertStatement.executeUpdate();
            isAdded = true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdded;
    }

//---------------------------------------------------------------------------------------------------------------------------------------//
    @Override
    public Boolean isCartExist(int userId) {
        PreparedStatement selectStatement;
        ResultSet rs;
        Boolean isExist = false;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT cartId FROM EcommerceDB.cart WHERE user_userId=" + userId);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------//

    public static void main(String[] args) {
        CartDAO cartDAO = new CartDAO();
        UserDAO udao = new UserDAO();

        /*Cart c = new Cart();
        c.setCartUser(udao.getUser(4));
        cartDAO.addEmptyCart(c);*/
        System.err.println(cartDAO.isCartExist(2));

    }

    /* public static void main(String[] args) {
        // UserDAO userDAO = new UserDAO();
        CartDAO cDao = new CartDAO();
        // User user = userDAO.getUser("Gehad");
        ProductDAO pdao = new ProductDAO();
        Cart c = cDao.getCartByUserID(1);
        Cart c2 = cDao.getCartByCartID(1);
        ArrayList<Cart> carts = cDao.getAllCars();
        // cDao.addNewProductToNewCart(3, 15, 10);
        // cDao.addNewProductToExistingCart(1, 19, 3);
        // cDao.removeProductFromCart(1, 14);
//        cDao.removeCartByCartID(1);
//        
//        ArrayList<CartItem> cartItems = new ArrayList<>();
//        cartItems.add(new CartItem(15,pdao.getSpecificProduct(16)));
//       Product p = new Product();
//       p.setProductId(17);
//       p.setProductName("jeans");
//       p.setDescription("jeans for women");
//       p.setQuantity(20);
//       p.setImage("aaaaaa");
//       p.setProductPrice(100.0);
//       p.setCatagory_catogeryId(1);
//       cartItems.add(new CartItem(20, p));
//       
//       cDao.updateExistingCart(2, cartItems);
       
        System.err.println(cDao.getProductQuantityInCart(2, 17));
    }*/


}
