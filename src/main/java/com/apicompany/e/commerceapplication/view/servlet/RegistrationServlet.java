package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.AuthController;
import com.apicompany.e.commerceapplication.business.CartController;
import com.apicompany.e.commerceapplication.dal.models.Cart;
import com.apicompany.e.commerceapplication.view.utility.Validation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {
    private Validation validation;
    private AuthController authController;
    private CartController cartController;

    @Override
    public void init() throws ServletException {
        validation = new Validation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String job = request.getParameter("job");
        String credit = request.getParameter("credit");
        Date birthdate = null;
        boolean isValidDate = false;
        try {
            birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate"));
            isValidDate = validation.isLegalAged(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean isValidName = validation.isName(name);
        boolean isValidPassword = validation.isValidPassword(password);
        boolean isValidPasswordMatches = validation.isPasswordMatches(password, repassword);
        boolean isValidEmail = validation.isEmail(email);
        boolean isValidAddress = !validation.isEmptyString(email);
        boolean isValidJob = !validation.isEmptyString(job);
        boolean isValidCredit = validation.isNumber(credit);


        if (isValidDate && isValidName && isValidPassword && isValidPasswordMatches && isValidEmail && isValidAddress && isValidJob && isValidCredit) {
            authController = new AuthController();
            authController.registerNewUser(name, password, email, address, job, Integer.parseInt(credit), birthdate);
            boolean registered = authController.isRegistered();
            if (registered) {
                cartController = new CartController();
                int userId = authController.getRegisteredUsedId();
                cartController.createNewCart(userId);
                response.sendRedirect("shop-login.jsp");
            } else {
                response.sendRedirect("shop-signup.jsp");
            }
        } else {
            response.sendRedirect("shop-signup.jsp");
        }
    }
}
