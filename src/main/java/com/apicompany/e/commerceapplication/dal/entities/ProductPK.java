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
public class ProductPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "productId")
    private int productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "category_categoryId")
    private int categorycategoryId;

    public ProductPK() {
    }

    public ProductPK(int productId, int categorycategoryId) {
        this.productId = productId;
        this.categorycategoryId = categorycategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategorycategoryId() {
        return categorycategoryId;
    }

    public void setCategorycategoryId(int categorycategoryId) {
        this.categorycategoryId = categorycategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) categorycategoryId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductPK)) {
            return false;
        }
        ProductPK other = (ProductPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        if (this.categorycategoryId != other.categorycategoryId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entities.ProductPK[ productId=" + productId + ", categorycategoryId=" + categorycategoryId + " ]";
    }
    
}
