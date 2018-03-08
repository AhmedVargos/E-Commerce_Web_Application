/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.admincontroller;

import com.apicompany.e.commerceapplication.dal.dao.daoimpl.CategoryDAO;
import com.apicompany.e.commerceapplication.dal.dao.daoint.CategoryDAOInt;
import com.apicompany.e.commerceapplication.dal.models.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gehad
 */
public class CategoryController {
    
    public void addCategory(String name){
        Category newCategory = new Category();
        newCategory.setCategoryName(name);
        CategoryDAOInt catDao = new CategoryDAO();
        catDao.insertCategory(newCategory);
    }
    
    public List<Category> getAllCategories(){
        CategoryDAOInt catDao = new CategoryDAO();
        return  catDao.getAllCatagory();
    }
    
}
