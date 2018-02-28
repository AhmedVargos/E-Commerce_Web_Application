/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.ProductDAOInt;
import com.apicompany.e.commerceapplication.dal.models.Product;
import java.util.ArrayList;

/**
 *
 * @author Vargos
 */
public class ProductDAO implements ProductDAOInt {

    @Override
    public ArrayList<Product> getallProducts() {
        return null;
    }

    @Override
    public boolean insertProduct(Product newProduct) {
      return true;
     
    }

    @Override
    public boolean deleteProduct(Product currentProduct) {
      return true; 
    }

    @Override
    public boolean updateProduct(Product currentproduct) {
       return true;
    }

    @Override
    public Product getSpecificProduct(int ProductId) {
       return null; 
    }

   
}
