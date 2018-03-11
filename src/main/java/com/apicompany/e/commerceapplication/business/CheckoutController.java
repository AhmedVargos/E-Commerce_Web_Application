/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.OrderDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.ProductDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Order;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.util.ArrayList;
import java.util.Date;
import javax.smartcardio.Card;

/**
 *
 * @author Vargos
 */
public class CheckoutController {
    //User user;
    //ArrayList<Product> cardProducts;
    //int totalPrice=0;

    public boolean checkUserFound(int Userid) {
        UserDAO myuserDao = new UserDAO();
        if (myuserDao.getUserById(Userid) == null) {
            return false;
        } else {
            return true;
        }

    }

    public boolean checkLimitRange(int userid, double TotalPrice) {

        if (TotalPrice <= getUser(userid).getCreditLimit()) {
            return true;
        } else {
            return false;
        }

    }

    public int CalaulatetotalPrice(int userid) {
        int totalprice = 0;
        CartDAO mycard = new CartDAO();
        Cart card;
        if((mycard.getCartByUserID(userid))!=null)
        {
            card=mycard.getCartByUserID(userid);
            ArrayList<CartItem> cardProducts = card.getCartItems();
        
        for (int i = 0; i < cardProducts.size(); i++) {
            double currentProductSalary = cardProducts.get(i).getProduct().getProductPrice();
            int currentProductQuantity = cardProducts.get(i).getQuantity();
            totalprice += currentProductSalary * currentProductQuantity;

        }
        }
        return totalprice;
    }

    public User getUser(int userId) {
        UserDAO myuserDao = new UserDAO();
        User user = myuserDao.getUserById(userId);
        return user;
    }

    public int MakeOrder(int userId) {
        CartDAO myCartDao = new CartDAO();
        Cart currentCart = myCartDao.getCartByUserID(userId);
        OrderDAO newOrderDAO = new OrderDAO();
        UserDAO userDao = new UserDAO();
        User currentUser = getUser(userId);
        //check if quantity found or not
        ArrayList<CartItem> totalItemsOfCards = currentCart.getCartItems();
        for(int i=0;i<totalItemsOfCards.size();i++)
        {
           if(totalItemsOfCards.get(i).getProduct().getQuantity()<totalItemsOfCards.get(i).getQuantity())
           {
              return -1;
           }
        }
        currentUser.setCreditLimit(currentUser.getCreditLimit() - CalaulatetotalPrice(userId));
        boolean resultUpdate = userDao.updateUser(currentUser);
        if (resultUpdate) {
            
            boolean addSuccess = newOrderDAO.addNewOrder(currentUser, totalItemsOfCards);
            if (addSuccess) {
                for (int i = 0; i < totalItemsOfCards.size(); i++) {
                    int pureProductQuantity = totalItemsOfCards.get(i).getProduct().getQuantity();
                    int buyProductQuantity = totalItemsOfCards.get(i).getQuantity();
                    int NewProductQuantity = pureProductQuantity - buyProductQuantity;
                    Product newproductValue = totalItemsOfCards.get(i).getProduct();
                    newproductValue.setQuantity(NewProductQuantity);
                    ProductDAO newProductDao = new ProductDAO();
                    newProductDao.updateProduct(newproductValue);
                }
                return 1;
            }
            else
            {
              return  -2;
            }
            
        } else {
            return -3;
        }
        
    }

    public boolean clearCard(int userid) {
        CartDAO removedCard = new CartDAO();
        boolean check = removedCard.removeCartByUserID(userid);
        return true;
    }

}
