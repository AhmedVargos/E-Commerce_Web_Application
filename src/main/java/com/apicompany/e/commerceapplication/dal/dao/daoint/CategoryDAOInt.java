package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Category;
import java.util.ArrayList;

public interface CategoryDAOInt {
    // get all gategory 
    public ArrayList<Category> getAllCatagory();
    /*Add new catagory
      we should pass Catagory object set All parameter of the object 
      EXCEPT>>>>>>>>>>catagory Id beacause it’s Auto increament
    */
    public boolean insertCategory(Category newCategory);
    /* update excisit category
      we should pass Catagory object set All parameter of the object (id,name) for right Update 
    this catagory with this id
    */
    public boolean updateCategory(Category currentCategory);
    /* Delete Current catagory
       send id of catagory only
    */
    public boolean deleteCategory(int CategoryId);
    /* get specific catagory by id 
       send id of catagory only
    */
    public Category getSpecieficCategory(int CategoryId);
}
