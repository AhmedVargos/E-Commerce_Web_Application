/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.ProductDAO;
import com.apicompany.e.commerceapplication.dal.models.Product;


/**
 *
 * @author Vargos
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


//        System.out.println(product);
//        List<Product> cartList = (List<Product>) session.getAttribute("cart_list");



        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        //TODO split string to get id and quantity
        int counter=0, i=0;
//        String []id_qunt={"",""};
        ArrayList<String> id_qunt= new ArrayList<>();
        StringTokenizer arr= new StringTokenizer(json,"&");
        while(arr.hasMoreElements()){
           String insideElement = arr.nextToken();
            StringTokenizer element= new StringTokenizer(insideElement,"=");
            while(element.hasMoreElements()){
//                System.out.println(element.nextToken());
                if(counter%2==0){
                    counter++;
                    continue;
                }
                id_qunt.add(i,element.nextToken() );
                i++;
                counter++;
            }

        }

        int id = Integer.parseInt(id_qunt.get(1));
        int quantity = Integer.parseInt(id_qunt.get(3));

        Product product;
        ProductDAO productDAO = new ProductDAO();
        product = productDAO.getSpecificProduct(id);
        Cart cart = new Cart();
        CartItem cartItem ;
        cartItem = new CartItem(quantity,product);
        ArrayList<CartItem> cartList = new ArrayList<>();

        HttpSession session = request.getSession();
        if(session.getAttribute("cart")==null){
            //if it was the first time to add to cart

           cartList.add(cartItem);
           cart.setCartItems(cartList);
           session.setAttribute("cart", cart);
        }else{
            //if cart has products
            Cart myCart = (Cart) session.getAttribute("cart");
            cartList = myCart.getCartItems();
            cartList.add(cartItem);
        }


    }

}
