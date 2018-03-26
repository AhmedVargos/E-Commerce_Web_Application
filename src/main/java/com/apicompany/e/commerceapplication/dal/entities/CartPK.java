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
public class CartPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cartId")
    private int cartId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_userId")
    private int useruserId;

    public CartPK() {
    }

    public CartPK(int cartId, int useruserId) {
        this.cartId = cartId;
        this.useruserId = useruserId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUseruserId() {
        return useruserId;
    }

    public void setUseruserId(int useruserId) {
        this.useruserId = useruserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cartId;
        hash += (int) useruserId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartPK)) {
            return false;
        }
        CartPK other = (CartPK) object;
        if (this.cartId != other.cartId) {
            return false;
        }
        if (this.useruserId != other.useruserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.CartPK[ cartId=" + cartId + ", useruserId=" + useruserId + " ]";
    }
    
}
