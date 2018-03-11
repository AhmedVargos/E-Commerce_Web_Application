/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/AdminCategoryServlet"})

public class AdminCategoryServlet extends HttpServlet {

    CategoryController categoryController;
    List<Category> allCategories;
    int pageSize;

    public AdminCategoryServlet() {
        categoryController = new CategoryController();
        allCategories = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNumber;
        List<Category> subCategorys;
        allCategories = categoryController.getAllCategories();

        if (allCategories != null) {
            if (allCategories.size() < 5) {
                pageSize = 1;
            } else {
                pageSize = allCategories.size() / 5;
            }

            if (request.getParameter("page").equals("")) {
                subCategorys = allCategories.subList(0, pageSize);
                request.setAttribute("Categories", subCategorys);
            } else {
                pageNumber = Integer.parseInt(request.getParameter("page"));
                switch (pageNumber) {
                    case 1:
                        subCategorys = allCategories.subList(0, pageSize);
                        request.setAttribute("Categories", subCategorys);
                        break;
                    case 5:
                        if ((pageNumber - 1) * pageSize < allCategories.size()) {
                            subCategorys = allCategories.subList((pageNumber - 1) * pageSize, allCategories.size());
                            request.setAttribute("Categories", subCategorys);
                        }
                        break;
                    default:
                        if ((pageNumber - 1) * pageSize < allCategories.size()) {
                            if (((pageNumber - 1) * pageSize) + pageSize < allCategories.size()) {
                                subCategorys = allCategories.subList((pageNumber - 1) * pageSize, ((pageNumber - 1) * pageSize) + pageSize);
                            } else {
                                subCategorys = allCategories.subList((pageNumber - 1) * pageSize, allCategories.size());
                            }
                            request.setAttribute("Categories", subCategorys);
                        }
                        break;
                }

            }
            if ((request.getParameter("delete") != null) && (Integer.parseInt(request.getParameter("delete")) == 1)) {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryController.deleteCategory(id);
            }
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
}
