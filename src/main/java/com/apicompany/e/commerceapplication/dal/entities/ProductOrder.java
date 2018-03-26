/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author szmoh
 */
@Entity
@Table(name = "product_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductOrder.findAll", query = "SELECT p FROM ProductOrder p")
    , @NamedQuery(name = "ProductOrder.findByProductproductId", query = "SELECT p FROM ProductOrder p WHERE p.productOrderPK.productproductId = :productproductId")
    , @NamedQuery(name = "ProductOrder.findByOrderorderId", query = "SELECT p FROM ProductOrder p WHERE p.productOrderPK.orderorderId = :orderorderId")
    , @NamedQuery(name = "ProductOrder.findByProductQuantityl", query = "SELECT p FROM ProductOrder p WHERE p.productQuantityl = :productQuantityl")})
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductOrderPK productOrderPK;
    @Column(name = "product_quantityl")
    private Integer productQuantityl;
    @JoinColumn(name = "order_orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Order1 order1;
    @JoinColumn(name = "product_productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderPK productOrderPK) {
        this.productOrderPK = productOrderPK;
    }

    public ProductOrder(int productproductId, int orderorderId) {
        this.productOrderPK = new ProductOrderPK(productproductId, orderorderId);
    }

    public ProductOrderPK getProductOrderPK() {
        return productOrderPK;
    }

    public void setProductOrderPK(ProductOrderPK productOrderPK) {
        this.productOrderPK = productOrderPK;
    }

    public Integer getProductQuantityl() {
        return productQuantityl;
    }

    public void setProductQuantityl(Integer productQuantityl) {
        this.productQuantityl = productQuantityl;
    }

    public Order1 getOrder1() {
        return order1;
    }

    public void setOrder1(Order1 order1) {
        this.order1 = order1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productOrderPK != null ? productOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrder)) {
            return false;
        }
        ProductOrder other = (ProductOrder) object;
        if ((this.productOrderPK == null && other.productOrderPK != null) || (this.productOrderPK != null && !this.productOrderPK.equals(other.productOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.ProductOrder[ productOrderPK=" + productOrderPK + " ]";
    }
    
}
