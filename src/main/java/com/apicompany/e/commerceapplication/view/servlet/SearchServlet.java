package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.HomeController;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.CartItem;
import com.apicompany.e.commerceapplication.dal.models.Product;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.apicompany.e.commerceapplication.view.servlet.AccessServlet.PRODUCTS_LIST;

@WebServlet(name = "SearchServlet",urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the search text and a list to search from then returns a list of result products
        HttpSession session = request.getSession();
        //TEST CODE adding a cart obj to the session for testing
        /*Cart mCart = new Cart();
        CartItem cartItem = new CartItem(2,new Product(1,"Item 1",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                11.00,
                50,
                1));

        CartItem cartItem1 = new CartItem(4,new Product(2,"Item 2",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                133.00,
                10,
                1));
        ArrayList<CartItem> products = new ArrayList<>();
        products.add(cartItem);
        products.add(cartItem1);
        mCart.setCartItems(products);
        session.setAttribute("CART",mCart);*/
        HomeController homeController = new HomeController();
        List<Product> productList = null;
        String productName = request.getParameter("name");
        int categoryNum = Integer.parseInt(request.getParameter("category"));

        if(categoryNum == -1){
            productList = homeController.getListOfProducts();
        }else {
            productList = homeController.getListOfProductsWithCategory(categoryNum);
        }

        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(homeController.searchProductsByName(productList,productName)));
        response.getWriter().close();
    }
}
