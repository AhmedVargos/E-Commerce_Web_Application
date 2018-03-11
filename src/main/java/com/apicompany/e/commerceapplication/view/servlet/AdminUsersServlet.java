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
import java.util.List;

@WebServlet(name = "AdminUsersServlet", urlPatterns = {"/AdminUsersServlet"})
public class AdminUsersServlet extends HttpServlet {

    private UsersController usersController;
    private ArrayList<User> allUsers;
    int pageSize;

    public AdminUsersServlet() {
        usersController = new UsersController();
        allUsers = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allUsers = usersController.getAllUsers();
        List<User> subUsers;
        int pageNumber;

        if (allUsers != null) {
            if (allUsers.size() < 5) {
                pageSize = 1;
            } else {
                pageSize = allUsers.size() / 5;

            }
            if (request.getParameter("page").equals("")) {
                subUsers = allUsers.subList(0, pageSize);
                request.setAttribute("users", subUsers);
            } else {
                pageNumber = Integer.parseInt(request.getParameter("page"));
                switch (pageNumber) {
                    case 1:
                        subUsers = allUsers.subList(0, pageSize);
                        request.setAttribute("users", subUsers);
                        break;
                    case 5:
                        if ((pageNumber - 1) * pageSize < allUsers.size()) {
                            subUsers = allUsers.subList((pageNumber - 1) * pageSize, allUsers.size());
                            request.setAttribute("users", subUsers);
                        }
                        break;
                    default:
                        if ((pageNumber - 1) * pageSize < allUsers.size()) {
                            if (((pageNumber - 1) * pageSize) + pageSize < allUsers.size()) {
                            subUsers = allUsers.subList((pageNumber - 1) * pageSize, ((pageNumber - 1) * pageSize) + pageSize);
                            }else {
                                subUsers = allUsers.subList((pageNumber - 1) * pageSize, allUsers.size());
                            }
                            request.setAttribute("users", subUsers);
                        }
                        break;
                }
            }
        }
        response.sendRedirect("Admin/users.jsp");
    }
}
