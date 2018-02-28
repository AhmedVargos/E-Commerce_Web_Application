package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.User;

import java.util.ArrayList;

public interface UserDAOInt {
    ArrayList<User> getAllUsers();
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(User user);
}
