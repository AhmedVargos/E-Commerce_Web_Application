/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author szmoh
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productPK.productId = :productId")
    , @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image")
    , @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice")
    , @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity")
    , @NamedQuery(name = "Product.findByCategorycategoryId", query = "SELECT p FROM Product p WHERE p.productPK.categorycategoryId = :categorycategoryId")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductPK productPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "productName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productPrice")
    private double productPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductCart> productCartList;
    @JoinColumn(name = "category_categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductOrder> productOrderList;

    public Product() {
    }

    public Product(ProductPK productPK) {
        this.productPK = productPK;
    }

    public Product(ProductPK productPK, String productName, String description, String image, double productPrice, int quantity) {
        this.productPK = productPK;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Product(int productId, int categorycategoryId) {
        this.productPK = new ProductPK(productId, categorycategoryId);
    }

    public ProductPK getProductPK() {
        return productPK;
    }

    public void setProductPK(ProductPK productPK) {
        this.productPK = productPK;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public List<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductCartList(List<ProductCart> productCartList) {
        this.productCartList = productCartList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productPK != null ? productPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productPK == null && other.productPK != null) || (this.productPK != null && !this.productPK.equals(other.productPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.Product[ productPK=" + productPK + " ]";
    }
    
}
