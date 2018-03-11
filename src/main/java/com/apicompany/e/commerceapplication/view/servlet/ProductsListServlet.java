package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.HomeController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.SHOP_TAG;
import static com.apicompany.e.commerceapplication.view.servlet.LoginServlet.PRODUCTS_LIST;

@WebServlet(name = "ProductsListServlet", urlPatterns = {"/ProductsListServlet"})
public class ProductsListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.valueOf(request.getParameter("catId"));
        HomeController homeController = new HomeController();
        if( categoryId== -1){
            request.setAttribute(PRODUCTS_LIST, homeController.getListOfProducts());
            request.setAttribute(SHOP_TAG,"Shop");

        }else {
            request.setAttribute(PRODUCTS_LIST, homeController.getListOfProductsWithCategory(categoryId));
            CategoryController categoryController = new CategoryController();
            String name = categoryController.getCategoryName(categoryId);
            request.setAttribute(SHOP_TAG,name);

        }
    }
}
