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
public class ProductOrderPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "product_productId")
    private int productproductId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_orderId")
    private int orderorderId;

    public ProductOrderPK() {
    }

    public ProductOrderPK(int productproductId, int orderorderId) {
        this.productproductId = productproductId;
        this.orderorderId = orderorderId;
    }

    public int getProductproductId() {
        return productproductId;
    }

    public void setProductproductId(int productproductId) {
        this.productproductId = productproductId;
    }

    public int getOrderorderId() {
        return orderorderId;
    }

    public void setOrderorderId(int orderorderId) {
        this.orderorderId = orderorderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productproductId;
        hash += (int) orderorderId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrderPK)) {
            return false;
        }
        ProductOrderPK other = (ProductOrderPK) object;
        if (this.productproductId != other.productproductId) {
            return false;
        }
        if (this.orderorderId != other.orderorderId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.ProductOrderPK[ productproductId=" + productproductId + ", orderorderId=" + orderorderId + " ]";
    }
    
}
