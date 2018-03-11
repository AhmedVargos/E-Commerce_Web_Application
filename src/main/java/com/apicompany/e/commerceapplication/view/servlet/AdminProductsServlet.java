package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.ProductsController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.apicompany.e.commerceapplication.dal.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminProductsServlet", urlPatterns = {"/AdminProductsServlet"})
public class AdminProductsServlet extends HttpServlet {

    private ProductsController productsController;
    private ArrayList<Product> allProducts;
    int pageSize;

    public AdminProductsServlet() {
        productsController = new ProductsController();
        allProducts = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> subProducts;
        int pageNumber;
        allProducts = productsController.getAllProducts();

        if (allProducts.size() < 5) {
            pageSize = 1;
        } else {
            pageSize = allProducts.size() / 5;
        }
        if (request.getParameter("page").equals("")) {
            subProducts = allProducts.subList(0, pageSize);
            request.setAttribute("products", subProducts);
        } else {
            pageNumber = Integer.parseInt(request.getParameter("page"));
            switch (pageNumber) {
                case 1:
                    subProducts = allProducts.subList(0, pageSize);
                    request.setAttribute("products", subProducts);
                    break;
                case 5:
                    if ((pageNumber - 1) * pageSize < allProducts.size()) {
                        subProducts = allProducts.subList((pageNumber - 1) * pageSize, allProducts.size());
                        request.setAttribute("products", subProducts);
                    }
                    break;
                default:
                    if ((pageNumber - 1) * pageSize < allProducts.size()) {
                        if (((pageNumber - 1) * pageSize) + pageSize < allProducts.size()) {
                            subProducts = allProducts.subList((pageNumber - 1) * pageSize, ((pageNumber - 1) * pageSize) + pageSize);
                        } else {
                            subProducts = allProducts.subList((pageNumber - 1) * pageSize, allProducts.size());
                        }
                        request.setAttribute("products", subProducts);
                    }
                    break;
            }
        }
        response.sendRedirect("Admin/products.jsp");

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productsController.deleteProduct(productId);
    }
}
