/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.utility;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import java.util.ArrayList;

/**
 *
 * @author body
 */
public class Functions {
    public static int CalaulatetotalPrice(int userid)
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
}
