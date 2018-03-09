/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/AdminCategoryServlet"})

public class AdminCategoryServlet extends HttpServlet {

    CategoryController categoryController;
    List<Category> allCategories;

    public AdminCategoryServlet() {
        categoryController = new CategoryController();
        allCategories = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        allCategories = categoryController.getAllCategories();
        request.setAttribute("Categories", allCategories);
        if ((request.getParameter("delete") != null) && (Integer.parseInt(request.getParameter("delete")) == 1)) {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryController.deleteCategory(id);
        }
       response.sendRedirect("Admin/categories.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addedCategoryName;
        String updatedCategoryName;
        int currentId;
        if (request.getParameter("name") != null) {
            addedCategoryName = request.getParameter("name");
            categoryController = new CategoryController();
            categoryController.addCategory(addedCategoryName);
        } else if (request.getParameter("cID_2") != null) {
            currentId = Integer.parseInt(request.getParameter("cID_2"));
            updatedCategoryName = request.getParameter("cName");
            categoryController.updateCategory(currentId, updatedCategoryName);
        }
        response.sendRedirect("Admin/categories.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
