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
    //tested
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

    //this method finds user by his id, returns null if user isn't exist
    //tested
    public User getUser(int id) {
        User user = new User();
        PreparedStatement selectStatement;
        ResultSet rs;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM USER WHERE userId = ?");
            selectStatement.setInt(1, id);
            rs = selectStatement.executeQuery();
            if (rs.next()) {
                user.setUserId(id);
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setJob(rs.getString("job"));
                user.setCreditLimit(rs.getInt("creditLimit"));
                user.setAddress(rs.getString("address"));
                user.setInterests(rs.getString("interests"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setBirthdate(rs.getDate("birthdate"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //this method finds user by his name, returns null if user isn't exist
    //tested
    public User getUser(String userName) {
        User user = new User();
        PreparedStatement selectStatement;
        ResultSet rs;
        try {
            selectStatement = dbHandler.getCon().prepareStatement("SELECT * FROM USER WHERE userName = ?");
            selectStatement.setString(1, userName);
            rs = selectStatement.executeQuery();
            if (rs.next());
            {
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setJob(rs.getString("job"));
                user.setCreditLimit(rs.getInt("creditLimit"));
                user.setAddress(rs.getString("address"));
                user.setInterests(rs.getString("interests"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setBirthdate(rs.getDate("birthdate"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //this methods is used to confirm login;it check if the username and password are correct or not
    // return the user if he existed, and null in case of invalid username/password
    public User isUserExist(String userName, String password) {
        User user = getUser(userName);
        if (user != null) {
            if (!user.getPassWord().equals(password)) {
                user = null;
            }
        }
        return user;
    }

    //-----------------------------------------------------------------------------------------------------------------------//
    // this method insert new user and return true in case of successful insertion, and false if insertion failed
    //tested
    public boolean addUser(User user) {
        PreparedStatement insertStatement;
        boolean isAdded;
        try {
            insertStatement = dbHandler.getCon().prepareStatement("INSERT INTO USER (userName,birthdate,"
                    + "password, email,job,creditLimit,address,interests,isAdmin) VALUES (?,?,?,?,?,?,?,?,?)");
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

    //tested
    public boolean updateUser(User user) {
        PreparedStatement updateStatement;
        boolean isUpdated;
        int userID = user.getUserId();

        try {
            updateStatement = dbHandler.getCon().prepareStatement("UPDATE USER SET userName = ? , birthdate = ? , "
                    + "password = ? , email = ? , job = ? , creditLimit = ? , address = ? , interests = ? , isAdmin = ? WHERE "
                    + "userId= ?");
            updateStatement.setString(1, user.getUserName());
            updateStatement.setDate(2, (Date) user.getBirthdate());
            updateStatement.setString(3, user.getPassWord());
            updateStatement.setString(4, user.getEmail());
            updateStatement.setString(5, user.getJob());

            updateStatement.setInt(6, user.getCreditLimit());
            updateStatement.setString(7, user.getAddress());
            updateStatement.setString(8, user.getInterests());
            updateStatement.setBoolean(9, user.isIsAdmin());
            updateStatement.setInt(10, userID);
            updateStatement.executeUpdate();
            isUpdated = true;
        } catch (SQLException ex) {
            isUpdated = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }
    
    //tested
    public boolean removeUser(User user) {
        PreparedStatement deleteStatement;
        boolean isRemoved;
        int userID = user.getUserId();
        try {
            deleteStatement = dbHandler.getCon().prepareStatement("DELETE FROM USER WHERE userId = ?");
            deleteStatement.setInt(1, userID);
            deleteStatement.executeUpdate();
            isRemoved = true;
        } catch (SQLException ex) {
            isRemoved = false;
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRemoved;
    }

    /* public static void main(String[] args) {
        UserDAO udao = new UserDAO();
        ArrayList<User> temp = udao.getAllUsers();
        System.out.println(temp.size());
        User u = new User();*/
 /* u.setUserName("David");
        u.setPassWord("123");
        u.setJob("Web developer");
        u.setIsAdmin(true);
        u.setInterests("Movies");
        u.setEmail("David@gmail.com");
        u.setCreditLimit(2000);
        Date date = new Date(2018-01-23);
        u.setBirthDay(date);
        u.setAddress("Haram");
        boolean added = udao.addUser(u);
        System.out.println(added);*/
    // test update
    //u = udao.getUser(2);
    // u.setPassWord("12345");
    //  boolean temp_2 = udao.removeUser(u);
    //System.out.println(temp_2);
    //u.setPassWord("12345");
    /* u= udao.isUserExist("David", "1245");
        if(u != null)
            System.out.println("exists" + u.getEmail());
        else
            System.out.println("Invalid");*/
    //}
}
