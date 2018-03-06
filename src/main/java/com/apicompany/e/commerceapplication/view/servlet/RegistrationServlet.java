package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.AuthController;
import com.apicompany.e.commerceapplication.view.utility.Validation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    Validation validation;
    AuthController authController;

    @Override
    public void init() throws ServletException {
        validation = new Validation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        boolean isEmail = validation.isEmail(email);
        boolean isPassword = validation.isValidPassword(password);
        boolean isPasswordMatches = validation.isPasswordMatches(password, repassword);

        if (isEmail && isPassword & isPasswordMatches) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedin", true);
            authController = new AuthController();
            authController.registerNewUser(email, password);
            boolean registered = authController.isRegistered();
            if (registered) {
                response.sendRedirect("shop-full-width.jsp");
            } else {
                response.sendRedirect("shop-login.jsp");
            }
        } else {
            response.sendRedirect("shop-login.jsp");
        }

    }
}
