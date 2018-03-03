/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.dal.models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vargos
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String product = request.getParameter("id");
//        List<Product> cartList = (List<Product>) session.getAttribute("cart_list");
        System.out.println(product);
    }

}
