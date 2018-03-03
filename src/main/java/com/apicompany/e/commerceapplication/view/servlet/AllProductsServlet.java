package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.HomeController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.PRODUCTS_LIST;
import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.SHOP_TAG;

@WebServlet(name = "AllProductsServlet", urlPatterns = {"/AllProductsServlet"})
public class AllProductsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the full list of products in the shop and open the shop home jsp
        HttpSession session = request.getSession();
        HomeController homeController = new HomeController();
        session.setAttribute(SHOP_TAG,"Shop");
        session.setAttribute(PRODUCTS_LIST,homeController.getListOfProducts());
        response.sendRedirect("shop-full-width.jsp");

    }
}
