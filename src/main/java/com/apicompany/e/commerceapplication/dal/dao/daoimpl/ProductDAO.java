/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.dao.daoimpl;

import com.apicompany.e.commerceapplication.dal.dao.daoint.ProductDAOInt;
import com.apicompany.e.commerceapplication.dal.database.DatabaseHandler;
import com.apicompany.e.commerceapplication.dal.entities.Product;
import com.apicompany.e.commerceapplication.dal.database.EntityManagerHandler;
//import com.apicompany.e.commerceapplication.dal.models.Product;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author Vargos
 */
public class ProductDAO implements ProductDAOInt {
// DatabaseHandler connHandler;

//    public ProductDAO() {
//        connHandler=DatabaseHandler.getDBInstance();
//    }
    private EntityManagerHandler entityManagerHandler;

    public ProductDAO() {
        entityManagerHandler = EntityManagerHandler.getEntityManagerHandler();
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProduct = new ArrayList<>();
        Product product;
        PreparedStatement getProduct;
        ResultSet rs;

        String queryString = "select p from Product p";
        Query query = entityManagerHandler.getEntityManager().createQuery(queryString);
        allProduct = (ArrayList<Product>) query.getResultList();
        return allProduct;

//        try {
//            String selectQuery="SELECT * FROM product";
//            getProduct = connHandler.getCon().prepareStatement(selectQuery);
//            rs = getProduct.executeQuery();
//            while (rs.next()) {
//                product = new Product();
//                product.setProductId(rs.getInt(DB_CoulmnNames.productId));
//                product.setProductName(rs.getString(DB_CoulmnNames.productName));
//                product.setDescription(rs.getString(DB_CoulmnNames.description));
//                product.setImage(rs.getString(DB_CoulmnNames.image));
//                product.setProductPrice(rs.getDouble(DB_CoulmnNames.productPrice));
//                product.setQuantity(rs.getInt(DB_CoulmnNames.quantity));
//                product.setCatagory_catogeryId(rs.getInt(DB_CoulmnNames.category_categoryId));
//                allProduct.add(product);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return allProduct;   
    }

    @Override
    public boolean insertProduct(Product newProduct) {
//        Product product=newProduct;
//        int res;
//        boolean resTest=false;
//        
        entityManagerHandler.getEntityManager().persist(newProduct);
        entityManagerHandler.getEntityManager().getTransaction().commit();
        return true;
//         try {
//             String insertQery="insert into product("+DB_CoulmnNames.productName+","+DB_CoulmnNames.description+","+DB_CoulmnNames.image+","+DB_CoulmnNames.productPrice+","+DB_CoulmnNames.quantity+","+DB_CoulmnNames.category_categoryId+")values(?,?,?,?,?,?)";
//             PreparedStatement insertstatment=connHandler.getCon().prepareStatement(insertQery);
//             insertstatment.setString(1,product.getProductName());
//             insertstatment.setString(2,product.getDescription());
//             insertstatment.setString(3,product.getImage());
//             insertstatment.setDouble(4,product.getProductPrice());
//             insertstatment.setInt(5,product.getQuantity());
//             insertstatment.setInt(6,product.getCatagory_catogeryId());
//             res=insertstatment.executeUpdate();
//             if(res>0)
//             {
//                 resTest=true;
//             }
//         } catch (SQLException ex) {
//             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//         }

    }

    @Override
    public boolean updateProduct(Product currentProduct) {
        Product product = currentProduct;
        int res;
        boolean resTest = false;
        
//        Query query = entityManagerHandler.getEntityManager().createQuery("update Product p set p = :newProduct where p = :oldProduct");
//        query.setParameter("newProduct", currentProduct);

//         try {
//             String Updatequery = "update product set "+DB_CoulmnNames.productName+"=?,"+DB_CoulmnNames.description+"=?,"+DB_CoulmnNames.image+"=?,"+DB_CoulmnNames.productPrice+"=?,"+DB_CoulmnNames.quantity+"=? where "+DB_CoulmnNames.productId+"=?";
//             PreparedStatement insertstatment=connHandler.getCon().prepareStatement(Updatequery);
//             insertstatment.setString(1,product.getProductName());
//             insertstatment.setString(2,product.getDescription());
//             insertstatment.setString(3,product.getImage());
//             insertstatment.setDouble(4,product.getProductPrice());
//             insertstatment.setInt(5,product.getQuantity());
//             insertstatment.setInt(6,product.getProductId());
//             res=insertstatment.executeUpdate();
//            if(res>0)
//             {
//                 resTest=true;
//             }
//            //connHandler.disconnectDB();
//         } catch (SQLException ex) {
//             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//         }
        return resTest;

    }

    @Override
    public boolean deleteProduct(int ProductId) {
        boolean resTest = false;

          Query query = entityManagerHandler.getEntityManager().createQuery("delete from Product p where p.productPK.productId = :productId");
          query.setParameter("productId", ProductId);
          query.executeUpdate();
          resTest=true;
//        try {
//            Connection con = (Connection) connHandler.getCon();
//            String testQery = "select * from product where " + DB_CoulmnNames.productId + "=" + ProductId;
//            PreparedStatement insertstatment = con.prepareStatement(testQery);
//            ResultSet res = insertstatment.executeQuery();
//            if (res.next()) {
//                String deleteQery = "delete from product where " + DB_CoulmnNames.productId + "=" + ProductId;
//                PreparedStatement insertstatment2 = con.prepareStatement(deleteQery);
//                int res2 = insertstatment2.executeUpdate();
//                if (res2 > 0) {
//                    resTest = true;
//                }
//                //connHandler.disconnectDB();
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return resTest;
    }

    @Override
    public Product getSpecificProduct(int ProductId) {
        Product product = new Product();
        Query query = entityManagerHandler.getEntityManager().createQuery("select p from Product p where p.productPK.productId = :productId");
        query.setParameter("productId", ProductId);
        product = (Product) query.getSingleResult();
//         try {
//             String selectQuery="select * from product where "+DB_CoulmnNames.productId+"="+ProductId;
//             PreparedStatement Selectstatment=connHandler.getCon().prepareStatement(selectQuery);
//             ResultSet res=Selectstatment.executeQuery();
//             if (res.next()) {
//                
//                product.setProductId(res.getInt(DB_CoulmnNames.productId));
//                product.setProductName(res.getString(DB_CoulmnNames.productName));
//                product.setDescription(res.getString(DB_CoulmnNames.description));
//                product.setImage(res.getString(DB_CoulmnNames.image));
//                product.setProductPrice(res.getDouble(DB_CoulmnNames.productPrice));
//                product.setQuantity(res.getInt(DB_CoulmnNames.quantity));
//                product.setCatagory_catogeryId(res.getInt(DB_CoulmnNames.category_categoryId));
//                
//            }
//             
//         }
//           catch (SQLException ex) {
//             Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//         }
        return product;
    }

    @Override
    public ArrayList<Product> getProductByPrice(int Productprice) {
        ArrayList<Product> allProduct = new ArrayList<>();
        Product product;
        Query query = entityManagerHandler.getEntityManager().createQuery("select p from Product p where p.productPrice = :productPrice");
        query.setParameter("productPrice", Productprice);
        allProduct = (ArrayList<Product>) query.getResultList();
//        try {
//            String selectQuery = "select * from product where " + DB_CoulmnNames.productPrice + "=" + Productprice;
//            PreparedStatement Selectstatment = connHandler.getCon().prepareStatement(selectQuery);
//            ResultSet res = Selectstatment.executeQuery();
//            while (res.next()) {
//                product = new Product();
//                product.setProductId(res.getInt(DB_CoulmnNames.productId));
//                product.setProductName(res.getString(DB_CoulmnNames.productName));
//                product.setDescription(res.getString(DB_CoulmnNames.description));
//                product.setImage(res.getString(DB_CoulmnNames.image));
//                product.setProductPrice(res.getDouble(DB_CoulmnNames.productPrice));
//                product.setQuantity(res.getInt(DB_CoulmnNames.quantity));
//                product.setCatagory_catogeryId(res.getInt(DB_CoulmnNames.category_categoryId));
//                allProduct.add(product);
//            }
//            //connHandler.disconnectDB();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return allProduct;
    }

    @Override
    public ArrayList<Product> getProductByCatagory(int Productcatagory) {
        ArrayList<Product> allProduct = new ArrayList<>();
        Product product;
        
        Query query = entityManagerHandler.getEntityManager().createQuery("select p from Product p where p.category = :productCat");
        query.setParameter("productCat", Productcatagory);
        allProduct = (ArrayList<Product>) query.getResultList();
//        
//        try {
//            String selectQuery = "select * from product where " + DB_CoulmnNames.category_categoryId + "=" + Productcatagory;
//            PreparedStatement Selectstatment = connHandler.getCon().prepareStatement(selectQuery);
//            ResultSet res = Selectstatment.executeQuery();
//            while (res.next()) {
//                product = new Product();
//                product.setProductId(res.getInt(DB_CoulmnNames.productId));
//                product.setProductName(res.getString(DB_CoulmnNames.productName));
//                product.setDescription(res.getString(DB_CoulmnNames.description));
//                product.setImage(res.getString(DB_CoulmnNames.image));
//                product.setProductPrice(res.getDouble(DB_CoulmnNames.productPrice));
//                product.setQuantity(res.getInt(DB_CoulmnNames.quantity));
//                product.setCatagory_catogeryId(res.getInt(DB_CoulmnNames.category_categoryId));
//                allProduct.add(product);
//            }
//            //connHandler.disconnectDB();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return allProduct;

    }
//    public static void main(String[] args) {
//        ProductDAO myproduct=new ProductDAO();
//       //Product newproduct = new Product("mkjhkj", "bjhj","f://dgndlkg",190.0,3 ,1);
// int test=myproduct.getProductId("mkjhkj");
//        
//         System.out.println(test);       
//    }   

    @Override
    public int getProductId(String productName) {
        int productId = 0;
        Query query = entityManagerHandler.getEntityManager().createQuery("select p.productPK.productId from Product p where p.productName = :productName");
        query.setParameter("productName", productName);
        productId = (int) query.getSingleResult();
        
        
//        try {
//            String selectQuery = "select " + DB_CoulmnNames.productId + " from product where " + DB_CoulmnNames.productName + "='" + productName + "'";
//            PreparedStatement Selectstatment = connHandler.getCon().prepareStatement(selectQuery);
//            ResultSet res = Selectstatment.executeQuery();
//            if (res.next()) {
//
//                productId = res.getInt(DB_CoulmnNames.productId);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return productId;

    }

}
