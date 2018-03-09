/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Vargos
 */
public class DatabaseHandler {

    private static DatabaseHandler databaseHandler = null;
    private Connection con;

    private DatabaseHandler() {
        connectToDB();
    }

    public static DatabaseHandler getDBInstance() {
        if (databaseHandler == null) {
            synchronized (DatabaseHandler.class) {
                if (databaseHandler == null) {
                    databaseHandler = new DatabaseHandler();
                }
            }
        }
        return databaseHandler;
    }

    private void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EcommerceDB", "root", "1412");
            System.err.println("connectToDB");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnectDB() throws SQLException {
        if (getCon() != null)
            getCon().close();
    }

    public Connection getCon() {
        return con;
    }
}
//jdbc:mysql://localhost:3306/EcommerceDB?zeroDateTimeBehavior=convertToNull [root on Default schema]
//3306 - 1010