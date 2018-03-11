package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.ProductsController;
import com.apicompany.e.commerceapplication.dal.models.Product;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "ImagesServlet", urlPatterns = {"/ImagesServlet"})
public class ImagesServlet extends HttpServlet {
    ProductsController productsController;
    private String folderPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        folderPath = config.getServletContext().getInitParameter("path");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        productsController = new ProductsController();

        Product product = productsController.getProductDetails(productId);

        File imageFile = new File(folderPath, product.getImage());
        byte[] img = Files.readAllBytes(imageFile.toPath());

        response.setContentType("application/octet-stream");
        response.setContentLength(img.length);
        response.getOutputStream().write(img);
        response.getWriter().close();
    }
}
