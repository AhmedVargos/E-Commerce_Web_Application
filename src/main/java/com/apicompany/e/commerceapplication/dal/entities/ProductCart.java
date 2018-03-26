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
@Table(name = "product_cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCart.findAll", query = "SELECT p FROM ProductCart p")
    , @NamedQuery(name = "ProductCart.findByProductproductId", query = "SELECT p FROM ProductCart p WHERE p.productCartPK.productproductId = :productproductId")
    , @NamedQuery(name = "ProductCart.findByCartcartId", query = "SELECT p FROM ProductCart p WHERE p.productCartPK.cartcartId = :cartcartId")
    , @NamedQuery(name = "ProductCart.findByProductQuantity", query = "SELECT p FROM ProductCart p WHERE p.productQuantity = :productQuantity")})
public class ProductCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductCartPK productCartPK;
    @Column(name = "product_quantity")
    private Integer productQuantity;
    @JoinColumn(name = "cart_cartId", referencedColumnName = "cartId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;
    @JoinColumn(name = "product_productId", referencedColumnName = "productId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProductCart() {
    }

    public ProductCart(ProductCartPK productCartPK) {
        this.productCartPK = productCartPK;
    }

    public ProductCart(int productproductId, int cartcartId) {
        this.productCartPK = new ProductCartPK(productproductId, cartcartId);
    }

    public ProductCartPK getProductCartPK() {
        return productCartPK;
    }

    public void setProductCartPK(ProductCartPK productCartPK) {
        this.productCartPK = productCartPK;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
        hash += (productCartPK != null ? productCartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCart)) {
            return false;
        }
        ProductCart other = (ProductCart) object;
        if ((this.productCartPK == null && other.productCartPK != null) || (this.productCartPK != null && !this.productCartPK.equals(other.productCartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.ProductCart[ productCartPK=" + productCartPK + " ]";
    }
    
}
