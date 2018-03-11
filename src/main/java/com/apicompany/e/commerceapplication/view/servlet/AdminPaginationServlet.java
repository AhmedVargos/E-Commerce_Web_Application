/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gehad
 */
@WebServlet(name = "AdminPaginationServlet", urlPatterns = {"/AdminPaginationServlet"})

public class AdminPaginationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNumber = 1;
        if (!request.getParameter("page").equals("")) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        HttpSession session = request.getSession();
        if (request.getParameter("order") != null && !request.getParameter("order").equals("")) {
            session.setAttribute("OrderPageNo", pageNumber);
            response.sendRedirect("Admin/orders.jsp");

        } else if (request.getParameter("category") != null &&!request.getParameter("category").equals("")) {
            session.setAttribute("CategoryPageNo", pageNumber);
            response.sendRedirect("Admin/categories.jsp");
        } else if (request.getParameter("user") != null && !request.getParameter("user").equals("")) {
            session.setAttribute("UserPageNo", pageNumber);
            response.sendRedirect("Admin/users.jsp");
        }

    }

}
