/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.OrderController;
import com.apicompany.e.commerceapplication.dal.models.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gehad
 */
@WebServlet(name = "AdminOrderServlet", urlPatterns = {"/AdminOrderServlet"})
public class AdminOrderServlet extends HttpServlet {

    OrderController orderController;
    List<Order> AllOrders;

    public AdminOrderServlet() {
        orderController = new OrderController();
        AllOrders = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AllOrders = orderController.getAllOrders();
        request.setAttribute("Orders", AllOrders);
        response.sendRedirect("Admin/orders.jsp");
    }


}
