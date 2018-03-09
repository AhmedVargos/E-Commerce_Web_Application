package com.apicompany.e.commerceapplication.view.servlet;

import com.apicompany.e.commerceapplication.business.UsersController;
import com.apicompany.e.commerceapplication.dal.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminUsersServlet", urlPatterns = {"/AdminUsersServlet"})
public class AdminUsersServlet extends HttpServlet {

    private UsersController usersController;
    private ArrayList<User> allUsers;

    public AdminUsersServlet() {
        usersController = new UsersController();
        allUsers = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allUsers = usersController.getAllUsers();
        request.setAttribute("users", allUsers);
    }
}
