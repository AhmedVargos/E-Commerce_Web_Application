/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.business;

import com.apicompany.e.commerceapplication.dal.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vargos
 */
public class HomeController {
    public HomeController() {
    }

    //returns a single product by it's id
    public Product getProductById(int id){
        //TODO get the product from the DB by id

        Product product = new Product("Item 1",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                101.00,
                50);

        return product;
    }

    //returns a list of all products in the DB
    public List<Product> getListOfProducts(){
        //TODO use the PRODUCT DAO to get a list of all products general without a specific category

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Item 1",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                101.00,
                50,
                1));

        productList.add(new Product("Item 2",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                22.00,
                10));

        productList.add(new Product("Item 3",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                17.00,
                56));

        productList.add(new Product("Item 4",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                65.00,
                100));
        return productList;
    }

    //returns a list of all products in a category takes category id
    public List<Product> getListOfProductsWithCategory(int categoryNum){
        //TODO use the PRODUCT DAO to get a list of all products in a specific category

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Item 1",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                101.00,
                50));

        productList.add(new Product("Item 2",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                22.00,
                10));

/*        productList.add(new Product("Item 3",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                17.00,
                56));

        productList.add(new Product("Item 4",
                "The item description Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in hendrerit purus",
                "",
                65.00,
                100));
*/
        return productList;
    }

    //returns a list of products searched by the name from the list passed to it
    public List<Product> searchProductsByName(List<Product> listToSearch, String name){

        //TODO search the DB by the name and return a list of products
        List<Product> result = new ArrayList<>();
        for (Product product: listToSearch) {
            if(product.getProductName().toLowerCase().contains(name.toLowerCase())){
                result.add(product);
            }
        }

        return result;
    }
}
