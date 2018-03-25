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
public class Order1PK implements Serializable {

    @Basic(optional = false)
    @Column(name = "orderId")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_userId")
    private int useruserId;

    public Order1PK() {
    }

    public Order1PK(int orderId, int useruserId) {
        this.orderId = orderId;
        this.useruserId = useruserId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
        hash += (int) orderId;
        hash += (int) useruserId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1PK)) {
            return false;
        }
        Order1PK other = (Order1PK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.useruserId != other.useruserId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entities.Order1PK[ orderId=" + orderId + ", useruserId=" + useruserId + " ]";
    }
    
}
