package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public interface UserDAOInt {
    ArrayList<User> getAllUsers();
    User getUserById(int id);
    User getUserByName(String userName);
    User getUserByEmail(String email);
    User isUserExist(String userName, String password);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(User user);
}
