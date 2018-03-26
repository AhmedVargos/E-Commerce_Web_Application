/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CartController;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CartDAO;
import com.apicompany.e.commerceapplication.dal.models.Cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
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
        if (br != null) {
            json = br.readLine();
        }

        //TODO split string to get id and quantity
        int counter = 0, i = 0;
//        String []id_qunt={"",""};
        ArrayList<String> id_qunt = new ArrayList<>();
        StringTokenizer arr = new StringTokenizer(json, "&");
        while (arr.hasMoreElements()) {
            String insideElement = arr.nextToken();
            StringTokenizer element = new StringTokenizer(insideElement, "=");
            while (element.hasMoreElements()) {
//                System.out.println(element.nextToken());
                if (counter % 2 == 0) {
                    counter++;
                    continue;
                }
                id_qunt.add(i, element.nextToken());
                i++;
                counter++;
            }

        }

        int id = Integer.parseInt(id_qunt.get(1));
        int quantity = Integer.parseInt(id_qunt.get(3));
        CartController cartController = new CartController();
        Cart cart;

//        CartItem cartItem ;
//        Product product;
//        ProductDAO productDAO = new ProductDAO();
//        product = productDAO.getSpecificProduct(id);
//        cartItem = new CartItem(quantity,product);
//        ArrayList<CartItem> cartList = new ArrayList<>();

        HttpSession session = request.getSession(false);
        if (session.getAttribute("cart") == null) {
            //if it was the first time to add to cart
            cart = cartController.addToCart(id, quantity);
            session.setAttribute("cart", cart);
        } else {
            //if cart has products
            Cart myCart = (Cart) session.getAttribute("cart");
            cart = cartController.appendToCart(id, quantity, myCart);
            session.setAttribute("cart", cart);
        }

        //if user is online insert products in DB
        if (session.getAttribute("loggedin") != null) {
            boolean logedIn = (boolean) session.getAttribute("loggedin");

            if (logedIn == true) {
                new Thread() {
                    public void run() {
                        //TODO use cart object on session
//                        boolean hasCart = false;
//                        int userId = (int) session.getAttribute("userId");
                        CartDAO cartDAO = new CartDAO();
                        int cartId = cart.getCartId();
                        cartDAO.addNewProductToExistingCart(cartId, id, quantity);


                        //check if user has cart ..............
//                    if (cartDAO.isCartExist(userId)==true){
//
//
//                        int cartId = cartDAO.getCartByUserID(userId).getCartId();
//                        cartDAO.addNewProductToExistingCart(cartId, id,quantity);
//                    }else {
//                        //if NOT
//                        cartDAO.addNewProductToNewCart(userId, id, quantity);
//                    }
                    }
                }.start();
            }
        }


    }
}