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

    CategoryDAOInt catDao;

    public void addCategory(String name) {
        Category newCategory = new Category();
        newCategory.setCategoryName(name);
        catDao = new CategoryDAO();
        catDao.insertCategory(newCategory);
    }

    public List<Category> getAllCategories() {
        catDao = new CategoryDAO();
        return catDao.getAllCatagory();
    }

    public void deleteCategory(int id) {
        catDao = new CategoryDAO();
        catDao.deleteCategory(id);
    }

    /*public void updateCategory(int id) {
        catDao = new CategoryDAO();
        Category currentCategory = new Category();

        currentCategory = catDao.getSpecieficCategory(id);
        catDao.updateCategory(currentCategory)

    }*/

}
