package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.entities.User;

import java.util.List;

public interface UserDAOInt {
    List<com.apicompany.e.commerceapplication.dal.entities.User> getAllUsers();
    com.apicompany.e.commerceapplication.dal.entities.User getUserById(int id);
    com.apicompany.e.commerceapplication.dal.entities.User getUserByName(String userName);
    com.apicompany.e.commerceapplication.dal.entities.User getUserByEmail(String email);
    com.apicompany.e.commerceapplication.dal.entities.User isUserExist(String userName, String password);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean removeUser(User user);
}
