package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.CategoryController;
import com.apicompany.e.commerceapplication.business.HomeController;
import com.apicompany.e.commerceapplication.dal.dao.daoimpl.ProductDAO;
import com.apicompany.e.commerceapplication.dal.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.SHOP_TAG;
import static com.apicompany.e.commerceapplication.view.servlet.LoginServlet.PRODUCTS_LIST;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductsListServlet", urlPatterns = {"/ProductsListServlet"})
public class ProductsListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId;
        if(!(request.getParameter("catId").equals("")))
        {
         categoryId = Integer.valueOf(request.getParameter("catId"));
        }
       else
        {
          categoryId=1;
        }
        
        if( categoryId== -1){
             int pageKey;
            if(!(request.getParameter("page").equals("")))
            {
                pageKey = Integer.parseInt(request.getParameter("page"));
            }
            else
            {
               pageKey=1;
            }
            ProductDAO productDAO=new ProductDAO();
            ArrayList<Product> Allproduct= productDAO.getAllProducts();
            int NumberOfProductInPage;
            if(Allproduct.size()<=8)
            {
               NumberOfProductInPage=Allproduct.size();
            }
            else
            {
               NumberOfProductInPage=Allproduct.size()/3;
            }
            int startPoint=NumberOfProductInPage*(pageKey-1);
            List<Product> newList =new ArrayList<>();
            if((startPoint+NumberOfProductInPage)<=Allproduct.size())
            {
                if(pageKey==3)
                    {
                      newList=Allproduct.subList(startPoint,Allproduct.size());
                    }
                    else
                    {
                      newList=Allproduct.subList(startPoint,(startPoint+NumberOfProductInPage));
                    }
            }
            request.setAttribute(PRODUCTS_LIST,newList);
            request.setAttribute(SHOP_TAG,"Shop");

        }else {
         
            HomeController homeController = new HomeController();
             List<Product> Allproduct= homeController.getListOfProductsWithCategory(categoryId);
              int pageKeyCat;
            if(!(request.getParameter("pagCat").equals("")))
            {
               pageKeyCat = Integer.parseInt(request.getParameter("pagCat"));
            }
            else
            {
               pageKeyCat=1;
            }
            int NumberOfProductInPage;
            if(Allproduct.size()<=8)
            {
               NumberOfProductInPage=Allproduct.size();
            }
            else
            {
               NumberOfProductInPage=Allproduct.size()/3;
            }
            int startPoint=NumberOfProductInPage*(pageKeyCat-1);
            List<Product> newList =new ArrayList<>();
            if((startPoint+NumberOfProductInPage)<=Allproduct.size())
            {
                if(pageKeyCat==3)
                    {
                      newList=Allproduct.subList(startPoint,Allproduct.size());
                    }
                    else
                    {
                      newList=Allproduct.subList(startPoint,(startPoint+NumberOfProductInPage));
                    }
            }
            request.setAttribute(PRODUCTS_LIST, newList);
            CategoryController categoryController = new CategoryController();
            String name = categoryController.getCategoryName(categoryId);
            request.setAttribute(SHOP_TAG,name);

        }
    }
}
