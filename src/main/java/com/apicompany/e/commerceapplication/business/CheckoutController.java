/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;

/**
 *
 * @author Vargos
 */
public class CheckoutController {
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
       
   
   
   
   return true;
   }
   public int CalaulatetotalPrice(int userid)
   { 
     UserDAO myuserDao=new UserDAO();
       User user=myuserDao.getUser(userid);
       CartDAO mycard=new CartDAO();
       
       
       
   
     return 1;
   }
}
