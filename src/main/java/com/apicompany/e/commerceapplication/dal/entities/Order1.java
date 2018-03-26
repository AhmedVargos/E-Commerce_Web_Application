/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author szmoh
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByOrderId", query = "SELECT o FROM Order1 o WHERE o.order1PK.orderId = :orderId")
    , @NamedQuery(name = "Order1.findByOrderDate", query = "SELECT o FROM Order1 o WHERE o.orderDate = :orderDate")
    , @NamedQuery(name = "Order1.findByUseruserId", query = "SELECT o FROM Order1 o WHERE o.order1PK.useruserId = :useruserId")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Order1PK order1PK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
    private List<ProductOrder> productOrderList;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Order1() {
    }

    public Order1(Order1PK order1PK) {
        this.order1PK = order1PK;
    }

    public Order1(Order1PK order1PK, Date orderDate) {
        this.order1PK = order1PK;
        this.orderDate = orderDate;
    }

    public Order1(int orderId, int useruserId) {
        this.order1PK = new Order1PK(orderId, useruserId);
    }

    public Order1PK getOrder1PK() {
        return order1PK;
    }

    public void setOrder1PK(Order1PK order1PK) {
        this.order1PK = order1PK;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @XmlTransient
    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
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
        hash += (order1PK != null ? order1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.order1PK == null && other.order1PK != null) || (this.order1PK != null && !this.order1PK.equals(other.order1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apicompany.e.commerceapplication.dal.entites.Order1[ order1PK=" + order1PK + " ]";
    }
    
}
