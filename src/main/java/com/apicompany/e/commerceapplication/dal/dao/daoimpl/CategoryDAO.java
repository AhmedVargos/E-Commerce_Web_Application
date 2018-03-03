/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.CategoryDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.models.Category;
import com.mysql.jdbc.Connection;
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
public class CategoryDAO implements CategoryDAOInt {
 DatabaseHandler connHandler;

    public CategoryDAO() {
        connHandler=DatabaseHandler.getDBInstance();
    }
    @Override
    public ArrayList<Category> getAllCatagory() {
          ArrayList<Category> allCategory = new ArrayList<>();
        Category category;
        PreparedStatement getCategory;
        ResultSet rs;
        try {
            String selectQuery="SELECT * FROM category";
            getCategory = connHandler.getCon().prepareStatement(selectQuery);
            rs = getCategory.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt(DB_CoulmnNames.categoryId));
                category.setCategoryName(rs.getString(DB_CoulmnNames.categoryName));
                allCategory.add(category);
            }
            connHandler.disconnectDB();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategory;      
    }

    @Override
    public boolean insertCategory(Category newCategory) {
        Category catagory=newCategory;
        int res;
        boolean resTest=false;
         try {
             String insertQery="insert into category("+DB_CoulmnNames.categoryName+")values(?)";
             PreparedStatement insertstatment=connHandler.getCon().prepareStatement(insertQery);
             insertstatment.setString(1,catagory.getCategoryName());
             res=insertstatment.executeUpdate();
             if(res>0)
             {
                 resTest=true;
             }
            connHandler.disconnectDB();
         } catch (SQLException ex) {
             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    return resTest;
    }

    @Override
    public boolean updateCategory(Category currentCategory) {
      Category catagory = currentCategory;
        int res;
        boolean resTest=false;
         try {
             String Updatequery = "update category set "+DB_CoulmnNames.categoryName+"=?  where "+DB_CoulmnNames.categoryId+"=?";
             PreparedStatement insertstatment=connHandler.getCon().prepareStatement(Updatequery);
             insertstatment.setString(1,catagory.getCategoryName());
             insertstatment.setInt(2,catagory.getCategoryId());
             res=insertstatment.executeUpdate();
            if(res>0)
             {
                 resTest=true;
             }
            connHandler.disconnectDB();
         } catch (SQLException ex) {
             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    return resTest;
        
    }

    @Override
    public boolean deleteCategory(int CategoryId) {
    boolean resTest=false;;
         try {
             Connection con = (Connection) connHandler.getCon();
             String testQery="select * from category where "+DB_CoulmnNames.categoryId+"="+CategoryId;
             PreparedStatement insertstatment=con.prepareStatement(testQery);
             ResultSet res=insertstatment.executeQuery();
             if(res.next())
             {
                  String deleteQery="delete from category where "+DB_CoulmnNames.categoryId+"="+CategoryId;
                  PreparedStatement insertstatment2=con.prepareStatement(deleteQery);
                  int res2=insertstatment2.executeUpdate();
                  if(res2>0)
             {
                 resTest=true;
             }
            connHandler.disconnectDB();
                  
             }  
         } catch (SQLException ex) {
             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
       return resTest;   
    
    }

    @Override
    public Category getSpecieficCategory(int CategoryId) {
      Category catagory=new  Category();
         try {
             String selectQuery="select * from category where "+DB_CoulmnNames.categoryId+"="+CategoryId;
             PreparedStatement Selectstatment=connHandler.getCon().prepareStatement(selectQuery);
             ResultSet res=Selectstatment.executeQuery();
             if (res.next()) {
                
                catagory.setCategoryId(res.getInt(DB_CoulmnNames.categoryId));
                catagory.setCategoryName(res.getString(DB_CoulmnNames.categoryName)); 
            }
             
         }
           catch (SQLException ex) {
             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return catagory;   
    
    }
//     public static void main(String[] args) {
//        CategoryDAO mycatagery = new CategoryDAO();
//        Category category =new Category();
//   boolean test = mycatagery.deleteCategory(2);
//         System.out.println(test);
//      
//         
//    }
   
}
