package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.HomeController;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.PRODUCTS_LIST;

@WebServlet(name = "SearchServlet",urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the search text and a list to search from then returns a list of result products
        HttpSession session = request.getSession();
        String productName = request.getParameter("name");
        List<Product> productList = (List<Product>) session.getAttribute(PRODUCTS_LIST);
        HomeController homeController = new HomeController();
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(homeController.searchProductsByName(productList,productName)));
        response.getWriter().close();
    }
}
