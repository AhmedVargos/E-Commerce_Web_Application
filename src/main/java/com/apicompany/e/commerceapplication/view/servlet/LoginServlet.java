package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.AuthController;
import com.apicompany.e.commerceapplication.business.CartController;
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

    private Validation validation;
    private AuthController authController;
    private CartController cartController;

    @Override
    public void init() throws ServletException {
        validation = new Validation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValidEmail = validation.isEmail(email);
        boolean isValidPassword = validation.isValidPassword(password);

        if (isValidEmail && isValidPassword) {
            authController = new AuthController();
            User user = authController.login(email, password);

            if (authController.isLoggedIn()) {
                HttpSession session = request.getSession(false);

                if (session != null) {
                    cartController = new CartController();
                    cartController.addCart((Cart) session.getAttribute("cart"));
                } else {
                    session = request.getSession(true);
                }

                session.setAttribute("userObj", user);
                session.setAttribute("loggedin", true);

                response.sendRedirect("shop-full-width.jsp");
            } else {
                response.sendRedirect("shop-login.jsp");
            }
        }
        else {
            response.sendRedirect("shop-login.jsp");
        }
    }

}
