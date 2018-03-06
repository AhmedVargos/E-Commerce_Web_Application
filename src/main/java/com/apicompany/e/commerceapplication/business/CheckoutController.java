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
      User user;
       ArrayList<Product> cardProducts;
       int totalPrice=0;
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
       
     UserDAO myuserDao=new UserDAO();
        user=myuserDao.getUser(userid);
       CartDAO mycard=new CartDAO();
      Cart card= mycard.getUserCart(user);
       //ArrayList<Product> cardProducts=card.getProducts();
       ProductDAO pp= new ProductDAO(); 
       cardProducts=pp.getAllProducts();
       int quantity =1;
         for(int i=0;i<cardProducts.size();i++)
         {
          totalPrice+=cardProducts.get(i).getProductPrice()*quantity;
         
         }
     return totalPrice ;
 }
   public User getUser(int userId)
   {
     //  UserDAO myuserDao=new UserDAO();
      // User user=myuserDao.getUser(userId);
       System.out.println("username: "+ user.getUserName());
       System.out.println("addres: "+ user.getAddress());
       return user;
   }
   public boolean MakeOrder()
   {
//       Order newOrder = new Order();
//          newOrder.setUser(user);
//          newOrder.setOrder_Date(new Date());
//          newOrder.setProducts(cardProducts);
          OrderDAO newOrderDAO = new OrderDAO();
          newOrderDAO.addNewOrder(user, cardProducts);
          UserDAO userDao=new UserDAO();
          user.setCreditLimit(user.getCreditLimit()-totalPrice);
          userDao.updateUser(user);
   return true;
   }
   
   public boolean clearCard()
   {
      CartDAO removedCard=new CartDAO();
      boolean check=removedCard.removeCart(user);
      return check;
   }
   
   
   
   
   
}
