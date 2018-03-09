package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;

import java.util.ArrayList;

public class UsersController {

    private ArrayList<User> allUsers;
    private UserDAO userDAO;

    public UsersController() {
        userDAO = new UserDAO();
    }

    public ArrayList<User> getAllUsers() {
        allUsers = userDAO.getAllUsers();
        return allUsers;
    }
}
