/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.UserDAOInt;
import com.apicompany.e.commerceapplication.dal.database.EntityManagerHandler;
import com.apicompany.e.commerceapplication.dal.entities.User;

import javax.persistence.Query;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Vargos
 */
public class UserDAO implements UserDAOInt {

    private EntityManagerHandler entityManagerHandler;

    public UserDAO() {
        entityManagerHandler = EntityManagerHandler.getEntityManagerHandler();
    }

    // this method returns all users
    //tested
    @Override
    public List<com.apicompany.e.commerceapplication.dal.entities.User> getAllUsers() {

        String queryString = "select u from User u";
        Query query = entityManagerHandler.getEntityManager().createQuery(queryString);
        List<com.apicompany.e.commerceapplication.dal.entities.User> allUsers = query.getResultList();
        return allUsers;
    }

    //this method finds user by his id, returns null if user isn't exist
    //tested
    @Override
    public User getUserById(int id) {
        String queryString = "select u from User u where u.userId = :id";
        Query query = entityManagerHandler.getEntityManager().createQuery(queryString).setParameter("id", id);
        com.apicompany.e.commerceapplication.dal.entities.User user = (com.apicompany.e.commerceapplication.dal.entities.User)query.getSingleResult();
        return user;
    }

    //this method finds user by his name, returns null if user isn't exist
    //tested
    @Override
    public User getUserByName(String userName) {
        String queryString = "select u from User u where u.userName = :name";
        Query query = entityManagerHandler.getEntityManager().createQuery(queryString).setParameter("name", userName);
        com.apicompany.e.commerceapplication.dal.entities.User user = (com.apicompany.e.commerceapplication.dal.entities.User)query.getSingleResult();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        String queryString = "select u from User u where u.email = :email";
        Query query = entityManagerHandler.getEntityManager().createQuery(queryString).setParameter("email", email);
        com.apicompany.e.commerceapplication.dal.entities.User user = (com.apicompany.e.commerceapplication.dal.entities.User)query.getSingleResult();
        return user;
    }

    //this methods is used to confirm login;it check if the username and password are correct or not
    // return the user if he existed, and null in case of invalid username/password
    @Override
    public User isUserExist(String email, String password) {
        User user = getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            user = null;
        }
        return user;
    }

    //-----------------------------------------------------------------------------------------------------------------------//
    // this method insert new user and return true in case of successful insertion, and false if insertion failed
    //tested
    @Override
    public boolean addUser(User user) {
        entityManagerHandler.getEntityManager().persist(user);
        entityManagerHandler.getEntityManager().getTransaction().commit();
        return true;
    }

    //tested
    @Override
    public boolean updateUser(User user) {
        PreparedStatement updateStatement;
        boolean isUpdated;
        int userID = user.getUserId();

        try {
            updateStatement = dbHandler.getCon().prepareStatement("UPDATE USER SET userName = ? , birthdate = ? , "
                    + "password = ? , email = ? , job = ? , creditLimit = ? , address = ? , interests = ? , isAdmin = ? WHERE "
                    + "userId= ?");
            updateStatement.setString(1, user.getUserName());
            updateStatement.setDate(2, new Date(user.getBirthdate().getTime()));
            updateStatement.setString(3, user.getPassword());
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
    @Override
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
