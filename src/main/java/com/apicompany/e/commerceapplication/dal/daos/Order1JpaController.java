/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.daos;

import com.apicompany.e.commerceapplication.dal.daos.exceptions.IllegalOrphanException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.NonexistentEntityException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.PreexistingEntityException;
import com.apicompany.e.commerceapplication.dal.entities.Order1;
import com.apicompany.e.commerceapplication.dal.entities.Order1PK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.apicompany.e.commerceapplication.dal.entities.User;
import com.apicompany.e.commerceapplication.dal.entities.ProductOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author szmoh
 */
public class Order1JpaController implements Serializable {

    public Order1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Order1 order1) throws PreexistingEntityException, Exception {
        if (order1.getOrder1PK() == null) {
            order1.setOrder1PK(new Order1PK());
        }
        if (order1.getProductOrderList() == null) {
            order1.setProductOrderList(new ArrayList<ProductOrder>());
        }
        order1.getOrder1PK().setUseruserId(order1.getUser().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user = order1.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getUserId());
                order1.setUser(user);
            }
            List<ProductOrder> attachedProductOrderList = new ArrayList<ProductOrder>();
            for (ProductOrder productOrderListProductOrderToAttach : order1.getProductOrderList()) {
                productOrderListProductOrderToAttach = em.getReference(productOrderListProductOrderToAttach.getClass(), productOrderListProductOrderToAttach.getProductOrderPK());
                attachedProductOrderList.add(productOrderListProductOrderToAttach);
            }
            order1.setProductOrderList(attachedProductOrderList);
            em.persist(order1);
            if (user != null) {
                user.getOrder1List().add(order1);
                user = em.merge(user);
            }
            for (ProductOrder productOrderListProductOrder : order1.getProductOrderList()) {
                Order1 oldOrder1OfProductOrderListProductOrder = productOrderListProductOrder.getOrder1();
                productOrderListProductOrder.setOrder1(order1);
                productOrderListProductOrder = em.merge(productOrderListProductOrder);
                if (oldOrder1OfProductOrderListProductOrder != null) {
                    oldOrder1OfProductOrderListProductOrder.getProductOrderList().remove(productOrderListProductOrder);
                    oldOrder1OfProductOrderListProductOrder = em.merge(oldOrder1OfProductOrderListProductOrder);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrder1(order1.getOrder1PK()) != null) {
                throw new PreexistingEntityException("Order1 " + order1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Order1 order1) throws IllegalOrphanException, NonexistentEntityException, Exception {
        order1.getOrder1PK().setUseruserId(order1.getUser().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 persistentOrder1 = em.find(Order1.class, order1.getOrder1PK());
            User userOld = persistentOrder1.getUser();
            User userNew = order1.getUser();
            List<ProductOrder> productOrderListOld = persistentOrder1.getProductOrderList();
            List<ProductOrder> productOrderListNew = order1.getProductOrderList();
            List<String> illegalOrphanMessages = null;
            for (ProductOrder productOrderListOldProductOrder : productOrderListOld) {
                if (!productOrderListNew.contains(productOrderListOldProductOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductOrder " + productOrderListOldProductOrder + " since its order1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getUserId());
                order1.setUser(userNew);
            }
            List<ProductOrder> attachedProductOrderListNew = new ArrayList<ProductOrder>();
            for (ProductOrder productOrderListNewProductOrderToAttach : productOrderListNew) {
                productOrderListNewProductOrderToAttach = em.getReference(productOrderListNewProductOrderToAttach.getClass(), productOrderListNewProductOrderToAttach.getProductOrderPK());
                attachedProductOrderListNew.add(productOrderListNewProductOrderToAttach);
            }
            productOrderListNew = attachedProductOrderListNew;
            order1.setProductOrderList(productOrderListNew);
            order1 = em.merge(order1);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getOrder1List().remove(order1);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getOrder1List().add(order1);
                userNew = em.merge(userNew);
            }
            for (ProductOrder productOrderListNewProductOrder : productOrderListNew) {
                if (!productOrderListOld.contains(productOrderListNewProductOrder)) {
                    Order1 oldOrder1OfProductOrderListNewProductOrder = productOrderListNewProductOrder.getOrder1();
                    productOrderListNewProductOrder.setOrder1(order1);
                    productOrderListNewProductOrder = em.merge(productOrderListNewProductOrder);
                    if (oldOrder1OfProductOrderListNewProductOrder != null && !oldOrder1OfProductOrderListNewProductOrder.equals(order1)) {
                        oldOrder1OfProductOrderListNewProductOrder.getProductOrderList().remove(productOrderListNewProductOrder);
                        oldOrder1OfProductOrderListNewProductOrder = em.merge(oldOrder1OfProductOrderListNewProductOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Order1PK id = order1.getOrder1PK();
                if (findOrder1(id) == null) {
                    throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Order1PK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 order1;
            try {
                order1 = em.getReference(Order1.class, id);
                order1.getOrder1PK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductOrder> productOrderListOrphanCheck = order1.getProductOrderList();
            for (ProductOrder productOrderListOrphanCheckProductOrder : productOrderListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Order1 (" + order1 + ") cannot be destroyed since the ProductOrder " + productOrderListOrphanCheckProductOrder + " in its productOrderList field has a non-nullable order1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User user = order1.getUser();
            if (user != null) {
                user.getOrder1List().remove(order1);
                user = em.merge(user);
            }
            em.remove(order1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Order1> findOrder1Entities() {
        return findOrder1Entities(true, -1, -1);
    }

    public List<Order1> findOrder1Entities(int maxResults, int firstResult) {
        return findOrder1Entities(false, maxResults, firstResult);
    }

    private List<Order1> findOrder1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order1.class));
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

    public Order1 findOrder1(Order1PK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order1.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrder1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order1> rt = cq.from(Order1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
