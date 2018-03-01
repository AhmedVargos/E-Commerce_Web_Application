/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.UserDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vargos
 */
public class UserDAO implements UserDAOInt{

    DatabaseHandler dbHandler;

    public UserDAO() {

        dbHandler = DatabaseHandler.getDBInstance();

    }

    // this method returns all users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        User user;
        PreparedStatement selectStatement;
        ResultSet rs;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM USER ");
            rs = selectStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setJob(rs.getString("job"));
                user.setCreditLimit(rs.getInt("creditLimit"));
                user.setAddress(rs.getString("address"));
                user.setInterests(rs.getString("interests"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    // this method insert new user and return true in case of successful insertion, and false if insertion failed
    public boolean addUser(User user) {
        PreparedStatement insertStatement;
        boolean isAdded;
        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO USER (?,?,?,?,?,?,?,?,?)");
            insertStatement.setString(1, user.getUserName());
            insertStatement.setDate(2, (Date) user.getBirthdate());
            insertStatement.setString(3, user.getPassWord());
            insertStatement.setString(4, user.getEmail());
            insertStatement.setString(5, user.getJob());
            insertStatement.setInt(6, user.getCreditLimit());
            insertStatement.setString(7, user.getAddress());
            insertStatement.setString(8, user.getInterests());
            insertStatement.setBoolean(9, user.isIsAdmin());
            insertStatement.executeUpdate();
            isAdded = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            isAdded = false;
        }
        return isAdded;
    }

    public boolean updateUser(User user) {
        PreparedStatement updateStatement;
        boolean isUpdated;
        int tempID = user.getUserId();

        try {
            updateStatement = dbHandler.getCon().prepareStatement("UPDATE USER SET userName = ? , birthdate = ? , "
                    + "password = ? , email = ? , job = ? , creditLimit = ? , address = ? , interests = ? , isAdmin = ? WHERE "
                    + "userId= ?");
            updateStatement.setString(1, user.getUserName());
//            updateStatement.setDate(2, (Date) user.getBirthDay());
            updateStatement.setString(3, user.getPassWord());
            updateStatement.setString(4, user.getEmail());
            updateStatement.setString(5, user.getJob());

            updateStatement.setInt(6, user.getCreditLimit());
            updateStatement.setString(7, user.getAddress());
            updateStatement.setString(8, user.getInterests());
            updateStatement.setBoolean(9, user.isIsAdmin());
            updateStatement.setInt(10, tempID);
            updateStatement.executeUpdate();
            isUpdated = true;
        } catch (SQLException ex) {
            isUpdated = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }
    
   /* public boolean removeUser(User user){
       // PreparedStatement deleteStatement;
       // boolean
    }*/


    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
        ArrayList<User> temp = udao.getAllUsers();

    }

}
