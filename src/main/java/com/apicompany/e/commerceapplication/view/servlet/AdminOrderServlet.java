/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.OrderController;
import com.apicompany.e.commerceapplication.dal.models.Category;
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
 * @author gehad
 */
@WebServlet(name = "AdminOrderServlet", urlPatterns = {"/AdminOrderServlet"})
public class AdminOrderServlet extends HttpServlet {

    OrderController orderController;
    List<Order> AllOrders;
    int pageSize;

    public AdminOrderServlet() {
        orderController = new OrderController();
        AllOrders = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNumber;
        List<Order> subOrders;
        AllOrders = orderController.getAllOrders();
        if (AllOrders != null) {
            if (AllOrders.size() > 0) {


                if (AllOrders.size() < 5) {
                    pageSize = 1;
                } else {
                    pageSize = AllOrders.size() / 5;
                }

                if (request.getParameter("page").equals("")) {
                    subOrders = AllOrders.subList(0, pageSize);
                    request.setAttribute("Orders", subOrders);
                } else {
                    pageNumber = Integer.parseInt(request.getParameter("page"));
                    switch (pageNumber) {
                        case 1:
                            subOrders = AllOrders.subList(0, pageSize);
                            request.setAttribute("Orders", subOrders);
                            break;
                        case 5:
                            if ((pageNumber - 1) * pageSize < AllOrders.size()) {
                                subOrders = AllOrders.subList((pageNumber - 1) * pageSize, AllOrders.size());
                                request.setAttribute("Orders", subOrders);
                            }
                            break;
                        default:
                            if ((pageNumber - 1) * pageSize < AllOrders.size()) {
                                if (((pageNumber - 1) * pageSize) + pageSize < AllOrders.size()) {
                                    subOrders = AllOrders.subList((pageNumber - 1) * pageSize, ((pageNumber - 1) * pageSize) + pageSize);
                                } else {
                                    subOrders = AllOrders.subList((pageNumber - 1) * pageSize, AllOrders.size());
                                }
                                request.setAttribute("Orders", subOrders);
                            }
                            break;
                    }

                }
            }
            response.sendRedirect("Admin/orders.jsp");
        }
    }

}
