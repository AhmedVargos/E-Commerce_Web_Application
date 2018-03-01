/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.models;

import java.util.Date;

/**
 *
 * @author Vargos
 */
public class User {

    private int userId;
    private String userName;
    private Date birthdate;
    private String passWord;
    private String email;
    private String job;
    private int creaditLimit;
    private String address;
    private String interests;
    private boolean isAdmin;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBirthdate(Date birthDay) {
        this.birthdate = birthDay;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getJob() {
        return job;
    }

    public int getCreditLimit() {
        return creaditLimit;
    }

    public String getAddress() {
        return address;
    }

    public String getInterests() {
        return interests;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setCreditLimit(int creaditLimit) {
        this.creaditLimit = creaditLimit;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
