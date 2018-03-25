/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author szmoh
 */
@Embeddable
public class ProductCartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "product_productId")
    private int productproductId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cart_cartId")
    private int cartcartId;

    public ProductCartPK() {
    }

    public ProductCartPK(int productproductId, int cartcartId) {
        this.productproductId = productproductId;
        this.cartcartId = cartcartId;
    }

    public int getProductproductId() {
        return productproductId;
    }

    public void setProductproductId(int productproductId) {
        this.productproductId = productproductId;
    }

    public int getCartcartId() {
        return cartcartId;
    }

    public void setCartcartId(int cartcartId) {
        this.cartcartId = cartcartId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productproductId;
        hash += (int) cartcartId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCartPK)) {
            return false;
        }
        ProductCartPK other = (ProductCartPK) object;
        if (this.productproductId != other.productproductId) {
            return false;
        }
        if (this.cartcartId != other.cartcartId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entities.ProductCartPK[ productproductId=" + productproductId + ", cartcartId=" + cartcartId + " ]";
    }
    
}
