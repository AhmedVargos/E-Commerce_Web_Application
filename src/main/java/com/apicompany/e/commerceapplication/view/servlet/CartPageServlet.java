package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CartPageController;
import com.apicompany.e.commerceapplication.business.HomeController;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.PRODUCTS_LIST;

@WebServlet(name = "CartPageServlet", urlPatterns = {"/CartPageServlet"})
public class CartPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. get received JSON data from request
  /*      BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
*/
        //First check if user is logged in or is a guest
        String json = request.getParameter("cart");
        HttpSession session = request.getSession(false);
        CartPageController cartPageController = new CartPageController();
        Cart userNewCart = cartPageController.updateUserCart(json);
        if(userNewCart != null){
            //Success in updating the cart
            session.setAttribute("cart", userNewCart);

            //response to the JS to refresh the page
            response.setContentType("application/json");
            //Gson gson = new Gson();
            response.getWriter().write("{status: 1}");
            response.getWriter().close();
        }else {
            //Failed to update the cart
            response.setContentType("application/json");
            //Gson gson = new Gson();
            response.getWriter().write("{status: 0}");
            response.getWriter().close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Get the search text and a list to search from then returns a list of result products
        HttpSession session = request.getSession();
        //TEST CODE adding a cart obj to the session for testing

        Cart mCart = (Cart) session.getAttribute("CART");
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(mCart));
        response.getWriter().close();
    }

}
