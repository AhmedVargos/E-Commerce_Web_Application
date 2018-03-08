package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;

import java.sql.Date;

public class AuthController {
    private UserDAO userDAO;
    private boolean isRegistered;
    private boolean isLoggedIn;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    private void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public int login(String email, String password) {
        int userId = userDAO.isUserExist(email, password);
        if (userId != -1) {
            setLoggedIn(true);
        } else {
            setLoggedIn(false);
        }
        return userId;
    }

    public void registerNewUser(String name, String password, String email, String address, String job, int credit) {
        User user = new User();
        user.setUserName(name);
        user.setPassWord(password);
        user.setEmail(email);
        user.setAddress(address);
        user.setJob(job);
        user.setCreditLimit(credit);
        String str = "2015-03-31";
        Date date = Date.valueOf(str);
        user.setBirthdate(date);

        userDAO = new UserDAO();
        boolean inserted = userDAO.addUser(user);
        setRegistered(inserted);
    }
}
