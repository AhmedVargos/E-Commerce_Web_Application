package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.UserDAO;
import com.apicompany.e.commerceapplication.dal.models.User;

public class AuthController {
    UserDAO userDAO;
    private boolean isRegistered;

    public void registerNewUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassWord(password);

        userDAO = new UserDAO();
        boolean inserted = userDAO.addUser(user);
        setRegistered(inserted);
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    private void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}
