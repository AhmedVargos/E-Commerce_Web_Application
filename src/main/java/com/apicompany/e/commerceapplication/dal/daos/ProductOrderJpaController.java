/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.daos;

import com.apicompany.e.commerceapplication.dal.daos.exceptions.NonexistentEntityException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.apicompany.e.commerceapplication.dal.entities.Order1;
import com.apicompany.e.commerceapplication.dal.entities.Product;
import com.apicompany.e.commerceapplication.dal.entities.ProductOrder;
import com.apicompany.e.commerceapplication.dal.entities.ProductOrderPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author szmoh
 */
public class ProductOrderJpaController implements Serializable {

    public ProductOrderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductOrder productOrder) throws PreexistingEntityException, Exception {
        if (productOrder.getProductOrderPK() == null) {
            productOrder.setProductOrderPK(new ProductOrderPK());
        }
        productOrder.getProductOrderPK().setOrderorderId(productOrder.getOrder1().getOrder1PK().getOrderId());
        productOrder.getProductOrderPK().setProductproductId(productOrder.getProduct().getProductPK().getProductId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 order1 = productOrder.getOrder1();
            if (order1 != null) {
                order1 = em.getReference(order1.getClass(), order1.getOrder1PK());
                productOrder.setOrder1(order1);
            }
            Product product = productOrder.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getProductPK());
                productOrder.setProduct(product);
            }
            em.persist(productOrder);
            if (order1 != null) {
                order1.getProductOrderList().add(productOrder);
                order1 = em.merge(order1);
            }
            if (product != null) {
                product.getProductOrderList().add(productOrder);
                product = em.merge(product);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductOrder(productOrder.getProductOrderPK()) != null) {
                throw new PreexistingEntityException("ProductOrder " + productOrder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductOrder productOrder) throws NonexistentEntityException, Exception {
        productOrder.getProductOrderPK().setOrderorderId(productOrder.getOrder1().getOrder1PK().getOrderId());
        productOrder.getProductOrderPK().setProductproductId(productOrder.getProduct().getProductPK().getProductId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductOrder persistentProductOrder = em.find(ProductOrder.class, productOrder.getProductOrderPK());
            Order1 order1Old = persistentProductOrder.getOrder1();
            Order1 order1New = productOrder.getOrder1();
            Product productOld = persistentProductOrder.getProduct();
            Product productNew = productOrder.getProduct();
            if (order1New != null) {
                order1New = em.getReference(order1New.getClass(), order1New.getOrder1PK());
                productOrder.setOrder1(order1New);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getProductPK());
                productOrder.setProduct(productNew);
            }
            productOrder = em.merge(productOrder);
            if (order1Old != null && !order1Old.equals(order1New)) {
                order1Old.getProductOrderList().remove(productOrder);
                order1Old = em.merge(order1Old);
            }
            if (order1New != null && !order1New.equals(order1Old)) {
                order1New.getProductOrderList().add(productOrder);
                order1New = em.merge(order1New);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getProductOrderList().remove(productOrder);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getProductOrderList().add(productOrder);
                productNew = em.merge(productNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductOrderPK id = productOrder.getProductOrderPK();
                if (findProductOrder(id) == null) {
                    throw new NonexistentEntityException("The productOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductOrderPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductOrder productOrder;
            try {
                productOrder = em.getReference(ProductOrder.class, id);
                productOrder.getProductOrderPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productOrder with id " + id + " no longer exists.", enfe);
            }
            Order1 order1 = productOrder.getOrder1();
            if (order1 != null) {
                order1.getProductOrderList().remove(productOrder);
                order1 = em.merge(order1);
            }
            Product product = productOrder.getProduct();
            if (product != null) {
                product.getProductOrderList().remove(productOrder);
                product = em.merge(product);
            }
            em.remove(productOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductOrder> findProductOrderEntities() {
        return findProductOrderEntities(true, -1, -1);
    }

    public List<ProductOrder> findProductOrderEntities(int maxResults, int firstResult) {
        return findProductOrderEntities(false, maxResults, firstResult);
    }

    private List<ProductOrder> findProductOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductOrder.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductOrder findProductOrder(ProductOrderPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductOrder> rt = cq.from(ProductOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
