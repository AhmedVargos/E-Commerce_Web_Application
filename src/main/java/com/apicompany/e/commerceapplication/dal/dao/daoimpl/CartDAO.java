/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.CartDAOInt;
import com.apicompany.e.commerceapplication.dal.database.EntityManagerHandler;
import com.apicompany.e.commerceapplication.dal.entities.*;
import com.apicompany.e.commerceapplication.dal.entities.CartItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Vargos
 */
public class CartDAO implements CartDAOInt {

    private EntityManager entityManager;

    public CartDAO() {
        entityManager = EntityManagerHandler.getEntityManagerHandler().getEntityManager();
    }

    //tested
    @Override
    public Cart getCartByUserID(int userId) {
        Cart cart;
        String queryString = "SELECT c FROM Cart c WHERE c.cartPK.useruserId = :useruserId";
        Query query = entityManager.createQuery(queryString).setParameter("useruserId", userId);
        cart = (Cart) query.getSingleResult();
        return cart;
    }

    //tested
    @Override
    public Cart getCartByCartID(int cartId) {
        Cart cart;
        String queryString = "SELECT c FROM Cart c WHERE c.cartPK.cartId = :cartId";
        Query query = entityManager.createQuery(queryString).setParameter("cartId", cartId);
        cart = (Cart) query.getSingleResult();
        return cart;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    //tested // ADDED BY AHMED
    @Override
    public Boolean addNewProductToExistingCart(int cartId, int productId, int Quantity) {
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.getSpecificProduct(productId) != null) {
            if (isProductExistInCart(cartId, productId)) {

                ProductCartPK productCartPK = new ProductCartPK();
                productCartPK.setCartcartId(cartId);
                productCartPK.setProductproductId(productId);

                entityManager.getTransaction().begin();
                int oldQuantity = getProductQuantityInCart(cartId, productId);
                Query query = entityManager.createQuery("update ProductCart pc set pc.productQuantity = :quantity where pc.productCartPK = :pk")
                        .setParameter("quantity", oldQuantity + Quantity)
                        .setParameter("pk", productCartPK);
                query.executeUpdate();
                entityManager.getTransaction().commit();

            } else {
                ProductCart productCart = new ProductCart(productId, cartId);
                productCart.setProductQuantity(Quantity);
                entityManager.getTransaction().begin();
                entityManager.persist(productCart);
                entityManager.getTransaction().commit();
            }
        }
        return true;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    @Override
    public Boolean removeCartByUserID(int userId) {
        Cart cart = new Cart();
        PreparedStatement deleteStatement;
        boolean isRemoved = false;

        cart = getCartByUserID(userId);
        if (cart != null) {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete FROM ProductCart pc where pc.cart = :c").setParameter("c", cart);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }

        return true;
    }

    //-------------------------------------------NEW AHMED-------------------------
    public Boolean addExistingCartItems(int cartId, ArrayList<CartItem> updatedItems) {
        PreparedStatement updateStatement;
        boolean isUpdated = false;
        Cart tempCart = getCartByCartID(cartId);
        if (tempCart != null) {
            for (CartItem updatedItem : updatedItems) {

                if (isProductExistInCart(cartId, updatedItem.getProduct().getProductPK().getProductId())) {
                    int oldQuantity = getProductQuantityInCart(cartId, updatedItem.getProduct().getProductPK().getProductId());

                    entityManager.getTransaction().begin();
                    ProductCartPK productCartPK = new ProductCartPK();
                    productCartPK.setProductproductId(updatedItem.getProduct().getProductPK().getProductId());
                    productCartPK.setCartcartId(cartId);
                    ProductCart productCart = entityManager.find(ProductCart.class, productCartPK);
                    productCart.setProductQuantity(oldQuantity + updatedItem.getQuantity());
                    entityManager.persist(productCart);
                    isUpdated = true;
                } else {
                    addNewProductToExistingCart(cartId, updatedItem.getProduct().getProductPK().getProductId(), updatedItem.getQuantity());
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
        int quantity = -1;
        if (isProductExistInCart(cartId, productId)) {
            entityManager.getTransaction().begin();
            ProductCartPK productCartPK = new ProductCartPK();
            productCartPK.setCartcartId(cartId);
            productCartPK.setProductproductId(productId);
            ProductCart productCart = entityManager.find(ProductCart.class, productCartPK);
            quantity = productCart.getProductQuantity();
        }
        return quantity;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------//
    //tested
    Boolean isProductExistInCart(int cartId, int productId) {

        boolean productExist = false;
        entityManager.getTransaction().begin();
        ProductCartPK productCartPK = new ProductCartPK();
        productCartPK.setCartcartId(cartId);
        productCartPK.setProductproductId(productId);
        ProductCart productCart = entityManager.find(ProductCart.class, productCartPK);
        entityManager.getTransaction().commit();

        if (productCart != null) {
            productExist = true;
        }

        return productExist;
    }

    public Boolean createEmptyCart(int userId) {

        entityManager.getTransaction().begin();
        Cart cart = new Cart();
        cart.setDate(new Date());
        User user = entityManager.find(User.class, userId);
        cart.setUser(user);
        entityManager.persist(cart);
        entityManager.getTransaction().commit();

        return true;
    }

    //-------------------- AHMED -----------
    public Boolean replaceOldCartWithNew(int cartId, ArrayList<CartItem> updatedItems) {

        entityManager.getTransaction().begin();
        Cart cart = entityManager.find(Cart.class, cartId);
        Query query = entityManager.createQuery("delete FROM ProductCart pc where pc.cart = :c").setParameter("c", cart);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        for (int i = 0; i < updatedItems.size(); i++) {
            addNewProductToExistingCart(cartId, updatedItems.get(i).getProduct().getProductPK().getProductId(), updatedItems.get(i).getQuantity());
        }

        return true;
    }
}
