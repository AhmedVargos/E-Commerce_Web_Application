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

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
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
import javax.servlet.annotation.WebInitParam;

@WebServlet(name = "AdminEditProductServlet", urlPatterns = {"/AdminEditProductServlet"},
        initParams = {
                @WebInitParam(name = "FILE_UPLOAD_PATH", value = "C:/Images")
        })
public class AdminEditProductServlet extends HttpServlet {

    private String fileUploadPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        fileUploadPath = config.getInitParameter("FILE_UPLOAD_PATH");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isFolderExists;

        PrintWriter out = response.getWriter();
        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                out.println("Nothing to upload");
                return;
            }

            String name = null, desc = null, fileName = null;
            int categoryId = -1, quantity = -1;
            double productPrice = -1;

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();

                    switch (fieldName) {
                        case "productCategory":
                            categoryId = Integer.parseInt(fieldValue);
                            break;
                        case "productName":
                            name = fieldValue;
                            break;
                        case "productDescription":
                            desc = fieldValue;
                            break;
                        case "productPrice":
                            productPrice = Double.parseDouble(fieldValue);
                            break;
                        case "productQuantity":
                            quantity = Integer.parseInt(fieldValue);
                            break;
                    }

                } else {
                    // Process form file field (input type="file").
                    File file = new File(fileUploadPath);
                    if (!file.exists()) {
                        isFolderExists = file.mkdir();
                    } else {
                        isFolderExists = true;
                    }

                    String fieldName = item.getFieldName();
                    fileName = FilenameUtils.getName(item.getName());
                    File uploadedFile = new File(fileUploadPath, fileName);

                    try {
                        item.write(uploadedFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                    InputStream fileContent = item.getInputStream();
            }

            ProductsController productsController = new ProductsController();
            productsController.updateProduct(categoryId, name, desc, productPrice, quantity, fileName);

        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductsController productsController = new ProductsController();
        CategoryController categoryController = new CategoryController();
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
