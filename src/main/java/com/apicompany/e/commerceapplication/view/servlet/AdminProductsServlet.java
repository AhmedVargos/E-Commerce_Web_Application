package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.ProductsController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import com.apicompany.e.commerceapplication.dal.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminProductsServlet", urlPatterns = {"/AdminProductsServlet"})
public class AdminProductsServlet extends HttpServlet {
    private ProductsController productsController;
    private ArrayList<Product> allProducts;

    public AdminProductsServlet() {
        productsController = new ProductsController();
        allProducts = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allProducts = productsController.getAllProducts();
        request.setAttribute("products", allProducts);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productsController.deleteProduct(productId);
    }
}
