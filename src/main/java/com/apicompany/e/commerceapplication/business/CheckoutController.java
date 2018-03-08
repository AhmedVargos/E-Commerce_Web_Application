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
   public  boolean checkUserFound(int Userid)
    {
       UserDAO myuserDao=new UserDAO();
       if(myuserDao.getUser(Userid)==null)
       {
         return false;
       }
       else
       {
         return true;
       }
    
    }
   public boolean checkLimitRange(int userid , double productPrice)
   {
       
     if(productPrice <= getUser(userid).getCreditLimit())
     {
      return true; 
     }   
     else
     {
     return false;
     }
   
   }
 
   public int CalaulatetotalPrice(int userid)
   { 
     int totalprice=0;  
     CartDAO mycard=new CartDAO();
     Cart card= mycard.getCartByUserID(userid);
       ArrayList<CartItem> cardProducts=card.getCartItems();
         for(int i=0;i<cardProducts.size();i++)
         {
          double currentProductSalary=cardProducts.get(i).getProduct().getProductPrice();
          int currentProductQuantity=cardProducts.get(i).getQuantity();
          totalprice+=currentProductSalary*currentProductQuantity;
         
         }
     return totalprice ;
 }
   public User getUser(int userId)
   {
        UserDAO myuserDao=new UserDAO();
       User user=myuserDao.getUser(userId);
       System.out.println("username: "+ user.getUserName());
       System.out.println("addres: "+ user.getAddress());
       return user;
   }
   public boolean MakeOrder(int userId)
   {
          CartDAO myCartDao=new CartDAO();
          Cart currentCart=myCartDao.getCartByUserID(userId);
          OrderDAO newOrderDAO = new OrderDAO();
//          newOrderDAO.addNewOrderCopy(getUser(userId), currentCart.getCartItems());
          UserDAO userDao=new UserDAO();
          User currentUser=getUser(userId);
          currentUser.setCreditLimit(currentUser.getCreditLimit()-CalaulatetotalPrice(userId));
          userDao.updateUser(currentUser);
          ArrayList<CartItem> totalItemsOfCards = currentCart.getCartItems();
          for(int i=0;i<totalItemsOfCards.size();i++)
          {
              int pureProductQuantity= totalItemsOfCards.get(i).getProduct().getQuantity();
             int buyProductQuantity=totalItemsOfCards.get(i).getQuantity();
             int NewProductQuantity=pureProductQuantity-buyProductQuantity;
             Product newproductValue= totalItemsOfCards.get(i).getProduct();
             newproductValue.setQuantity(NewProductQuantity);
             ProductDAO newProductDao=new ProductDAO();
             newProductDao.updateProduct(newproductValue);
          }
   return true;
   }
   
   public boolean clearCard(int userid)
   {
      CartDAO removedCard=new CartDAO();
      boolean check=removedCard.removeCartByUserID(userid);
      return true;
   }
   
   
   
   
   
}
