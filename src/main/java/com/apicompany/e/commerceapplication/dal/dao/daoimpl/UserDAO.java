/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.UserDAOInt;
import com.apicompany.e.commerceapplication.dal.database.EntityManagerHandler;
import com.apicompany.e.commerceapplication.dal.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vargos
 */
public class UserDAO implements UserDAOInt {

    private EntityManager entityManager;

    public UserDAO() {
        entityManager = EntityManagerHandler.getEntityManagerHandler().getEntityManager();
    }

    // this method returns all users
    //tested
    @Override
    public List<User> getAllUsers() {

        String queryString = "select u from User u";
        Query query = entityManager.createQuery(queryString);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    //this method finds user by his id, returns null if user isn't exist
    //tested
    @Override
    public User getUserById(int id) {
        String queryString = "select u from User u where u.userId = :id";
        Query query = entityManager.createQuery(queryString).setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        String queryString = "select u from User u where u.email = :email";
        Query query = entityManager.createQuery(queryString).setParameter("email", email);
        User user = (User) query.getSingleResult();
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
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return true;
    }

    //tested
    @Override
    public boolean updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return true;
    }
}