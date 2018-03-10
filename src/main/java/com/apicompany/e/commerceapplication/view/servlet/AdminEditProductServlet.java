package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.ProductsController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "AdminEditProductServlet", urlPatterns = {"/AdminEditProductServlet"})
public class AdminEditProductServlet extends HttpServlet {

    private ProductsController productsController;
    private CategoryController categoryController;

    public AdminEditProductServlet() {
        productsController = new ProductsController();
        categoryController = new CategoryController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productsController.getProductDetails(productId);
        List<Category> allCategories = categoryController.getAllCategories();

        for (int i = 0; i < allCategories.size(); i++) {
            if (allCategories.get(i).getCategoryId() == product.getCatagory_catogeryId()) {
                Collections.swap(allCategories, i, 0);
            }
        }

        response.setContentType("application/json");
        Gson gson = new Gson();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("product", product);
        hashMap.put("categories", allCategories);

        response.getWriter().write(gson.toJson(hashMap));
        response.getWriter().close();
    }
}
