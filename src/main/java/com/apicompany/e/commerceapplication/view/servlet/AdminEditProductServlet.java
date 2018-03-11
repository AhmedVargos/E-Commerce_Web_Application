package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.ProductsController;
import com.apicompany.e.commerceapplication.dal.models.Category;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "AdminEditProductServlet", urlPatterns = {"/AdminEditProductServlet"})
public class AdminEditProductServlet extends HttpServlet {

    private ProductsController productsController;
    private CategoryController categoryController;

    public AdminEditProductServlet() {
        productsController = new ProductsController();
        categoryController = new CategoryController();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                out.println("Nothing to upload");
                return;
            }

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    if (fieldName.equals("imageFile")) {

                    }
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
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
