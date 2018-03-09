package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.UsersController;
import com.apicompany.e.commerceapplication.dal.models.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminUserDetailsServlet", urlPatterns = {"/AdminUserDetailsServlet"})
public class AdminUserDetailsServlet extends HttpServlet {

    private UsersController usersController;

    public AdminUserDetailsServlet() {
        usersController = new UsersController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = usersController.getUserById(userId);
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(user));
        response.getWriter().close();
    }
}
