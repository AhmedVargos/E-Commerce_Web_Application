package com.apicompany.e.commerceapplication.dal.dao.daoint;

import com.apicompany.e.commerceapplication.dal.models.Product;

import java.util.ArrayList;

public interface ProductDAOInt {
    //get all products to view in homepage
    public ArrayList<Product> getAllProducts();
   /*Add new product by admin
      we should pass product object set All parameter of the object 
      EXCEPT>>>>>>>>>>product Id beacause it’s Auto increament
    */
    public boolean insertProduct(Product newProduct);
    /* Delete Current product
       send id of product only
    */
    public boolean deleteProduct(int ProductId);
    /* update excisit product by admin
      we should pass product object set >>>>>All parameter<<<<<of the object for right Update 
      this product with this id
    */
    public boolean updateProduct(Product currentproduct);
    /*get specific product to view it’s detail when click view product
       send id of product only  
    */
    public Product getSpecificProduct(int ProductId);
    /* get speciefic product when search with price
        send price of product only 
       return List of All product with this price
    */
    public ArrayList<Product> getProductByPrice(int Productprice); 
    /*get specific product when search with catagory
       send catagory of product only 
       return List of All product with this catagory
    */
    public ArrayList<Product> getProductByCatagory(int Productcatagory);  
}
