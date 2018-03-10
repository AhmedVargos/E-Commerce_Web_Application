package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.AuthController;
import com.apicompany.e.commerceapplication.business.CartController;
import com.apicompany.e.commerceapplication.business.HomeController;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.dal.models.User;
import com.apicompany.e.commerceapplication.view.utility.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    public static final String PRODUCTS_LIST = "PRODUCTS_LIST";
    public static final String SHOP_TAG = "SHOP_TAG";

    private Validation validation;
    private AuthController authController;
    private CartController cartController;

    public LoginServlet() {
        authController = new AuthController();
        cartController = new CartController();
        validation = new Validation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValidEmail = validation.isEmail(email);
        boolean isValidPassword = validation.isValidPassword(password);

        if (isValidEmail && isValidPassword) {
            User user = authController.login(email, password);

            if (authController.isLoggedIn()) {
                HttpSession session = request.getSession(false);

                if (session != null) {
                    Cart userOldCart = cartController.getCurrentCart(user.getUserId());
                    if (session.getAttribute("cart") != null) {
                        Cart cart = (Cart) session.getAttribute("cart");
                        cart.setCartId(userOldCart.getCartId());
                        cartController.addProductsToCart(cart);
                    }
                    session.setAttribute("cart", cartController.getCurrentCart(user.getUserId()));
                } else {
                    session = request.getSession(true);
                    session.setAttribute("cart", cartController.getCurrentCart(user.getUserId()));
                }

                if (user.isIsAdmin()) {
                    session.setAttribute("isAdmin", true);
                }

                session.setAttribute("userObj", user);
                session.setAttribute("loggedin", true);

                HomeController homeController = new HomeController();
                session.setAttribute(SHOP_TAG, "Shop");
                //session.setAttribute(PRODUCTS_LIST, homeController.getListOfProducts());

                response.sendRedirect("shop-full-width.jsp");
            } else {
                response.sendRedirect("shop-login.jsp");
            }
        } else {
            response.sendRedirect("shop-login.jsp");
        }
    }

}
