/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

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

    CategoryDAOInt catDao;

    public CategoryController() {
        catDao = new CategoryDAO();
    }

    
    public void addCategory(String name) {
        Category newCategory = new Category();
        newCategory.setCategoryName(name);
        catDao.insertCategory(newCategory);
    }

    public List<Category> getAllCategories() {
        return catDao.getAllCatagory();
    }

    public void deleteCategory(int id) {
        catDao.deleteCategory(id);
    }
    
    public void updateCategory(int id, String name){
        Category category = new Category();
        category.setCategoryId(id);
        category.setCategoryName(name);
       catDao.updateCategory(category);
    }
    /*public void updateCategory(int id) {
        catDao = new CategoryDAO();
        Category currentCategory = new Category();

        currentCategory = catDao.getSpecieficCategory(id);
        catDao.updateCategory(currentCategory)

    }*/


    public String getCategoryName(int id){
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();

        category = categoryDAO.getSpecieficCategory(id);

        return category.getCategoryName();
    }
}
