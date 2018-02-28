package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Product;
import java.util.ArrayList;

public interface ProductDAOInt {
    //get all products to view in homepage
   public  ArrayList<Product> getallProducts();
   //insert new product by admin 
   public boolean insertProduct(Product newProduct);
   //delete specific product by admin
   public boolean deleteProduct(Product currentProduct);
   //update specific product by admin
   public boolean updateProduct(Product currentproduct);
   //get specific product to view it’s detail when click view product
   public Product getSpecificProduct(int ProductId); 
   
}
