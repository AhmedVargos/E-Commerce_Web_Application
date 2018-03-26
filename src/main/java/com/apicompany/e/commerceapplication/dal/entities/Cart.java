/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author szmoh
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c")
    , @NamedQuery(name = "Cart.findByCartId", query = "SELECT c FROM Cart c WHERE c.cartPK.cartId = :cartId")
    , @NamedQuery(name = "Cart.findByDate", query = "SELECT c FROM Cart c WHERE c.date = :date")
    , @NamedQuery(name = "Cart.findByUseruserId", query = "SELECT c FROM Cart c WHERE c.cartPK.useruserId = :useruserId")})
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CartPK cartPK;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<ProductCart> productCartList;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    @Transient
    private ArrayList<CartItem> cartItems;

    public Cart() {
    }

    public Cart(CartPK cartPK) {
        this.cartPK = cartPK;
    }

    public Cart(int cartId, int useruserId) {
        this.cartPK = new CartPK(cartId, useruserId);
    }

    public CartPK getCartPK() {
        return cartPK;
    }

    public void setCartPK(CartPK cartPK) {
        this.cartPK = cartPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public List<ProductCart> getProductCartList() {
        return productCartList;
    }

    public void setProductCartList(List<ProductCart> productCartList) {
        this.productCartList = productCartList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartPK != null ? cartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartPK == null && other.cartPK != null) || (this.cartPK != null && !this.cartPK.equals(other.cartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.Cart[ cartPK=" + cartPK + " ]";
    }
    
}
