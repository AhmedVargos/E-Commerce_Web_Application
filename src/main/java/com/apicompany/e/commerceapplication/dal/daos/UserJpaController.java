/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.daos;

import com.apicompany.e.commerceapplication.dal.daos.exceptions.IllegalOrphanException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.NonexistentEntityException;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.apicompany.e.commerceapplication.dal.entities.Cart;

import java.util.ArrayList;
import java.util.List;

import com.apicompany.e.commerceapplication.dal.entities.Order1;
import com.apicompany.e.commerceapplication.dal.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author szmoh
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getCartList() == null) {
            user.setCartList(new ArrayList<Cart>());
        }
        if (user.getOrder1List() == null) {
            user.setOrder1List(new ArrayList<Order1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cart> attachedCartList = new ArrayList<Cart>();
            for (Cart cartListCartToAttach : user.getCartList()) {
                cartListCartToAttach = em.getReference(cartListCartToAttach.getClass(), cartListCartToAttach.getCartId());
                attachedCartList.add(cartListCartToAttach);
            }
            user.setCartList(attachedCartList);
            List<Order1> attachedOrder1List = new ArrayList<Order1>();
            for (Order1 order1ListOrder1ToAttach : user.getOrder1List()) {
                order1ListOrder1ToAttach = em.getReference(order1ListOrder1ToAttach.getClass(), order1ListOrder1ToAttach.getOrder1PK());
                attachedOrder1List.add(order1ListOrder1ToAttach);
            }
            user.setOrder1List(attachedOrder1List);
            em.persist(user);
            for (Cart cartListCart : user.getCartList()) {
                User oldUseruserIdOfCartListCart = cartListCart.getUseruserId();
                cartListCart.setUseruserId(user);
                cartListCart = em.merge(cartListCart);
                if (oldUseruserIdOfCartListCart != null) {
                    oldUseruserIdOfCartListCart.getCartList().remove(cartListCart);
                    oldUseruserIdOfCartListCart = em.merge(oldUseruserIdOfCartListCart);
                }
            }
            for (Order1 order1ListOrder1 : user.getOrder1List()) {
                User oldUserOfOrder1ListOrder1 = order1ListOrder1.getUser();
                order1ListOrder1.setUser(user);
                order1ListOrder1 = em.merge(order1ListOrder1);
                if (oldUserOfOrder1ListOrder1 != null) {
                    oldUserOfOrder1ListOrder1.getOrder1List().remove(order1ListOrder1);
                    oldUserOfOrder1ListOrder1 = em.merge(oldUserOfOrder1ListOrder1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            List<Cart> cartListOld = persistentUser.getCartList();
            List<Cart> cartListNew = user.getCartList();
            List<Order1> order1ListOld = persistentUser.getOrder1List();
            List<Order1> order1ListNew = user.getOrder1List();
            List<String> illegalOrphanMessages = null;
            for (Cart cartListOldCart : cartListOld) {
                if (!cartListNew.contains(cartListOldCart)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cart " + cartListOldCart + " since its useruserId field is not nullable.");
                }
            }
            for (Order1 order1ListOldOrder1 : order1ListOld) {
                if (!order1ListNew.contains(order1ListOldOrder1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order1 " + order1ListOldOrder1 + " since its user field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cart> attachedCartListNew = new ArrayList<Cart>();
            for (Cart cartListNewCartToAttach : cartListNew) {
                cartListNewCartToAttach = em.getReference(cartListNewCartToAttach.getClass(), cartListNewCartToAttach.getCartId());
                attachedCartListNew.add(cartListNewCartToAttach);
            }
            cartListNew = attachedCartListNew;
            user.setCartList(cartListNew);
            List<Order1> attachedOrder1ListNew = new ArrayList<Order1>();
            for (Order1 order1ListNewOrder1ToAttach : order1ListNew) {
                order1ListNewOrder1ToAttach = em.getReference(order1ListNewOrder1ToAttach.getClass(), order1ListNewOrder1ToAttach.getOrder1PK());
                attachedOrder1ListNew.add(order1ListNewOrder1ToAttach);
            }
            order1ListNew = attachedOrder1ListNew;
            user.setOrder1List(order1ListNew);
            user = em.merge(user);
            for (Cart cartListNewCart : cartListNew) {
                if (!cartListOld.contains(cartListNewCart)) {
                    User oldUseruserIdOfCartListNewCart = cartListNewCart.getUseruserId();
                    cartListNewCart.setUseruserId(user);
                    cartListNewCart = em.merge(cartListNewCart);
                    if (oldUseruserIdOfCartListNewCart != null && !oldUseruserIdOfCartListNewCart.equals(user)) {
                        oldUseruserIdOfCartListNewCart.getCartList().remove(cartListNewCart);
                        oldUseruserIdOfCartListNewCart = em.merge(oldUseruserIdOfCartListNewCart);
                    }
                }
            }
            for (Order1 order1ListNewOrder1 : order1ListNew) {
                if (!order1ListOld.contains(order1ListNewOrder1)) {
                    User oldUserOfOrder1ListNewOrder1 = order1ListNewOrder1.getUser();
                    order1ListNewOrder1.setUser(user);
                    order1ListNewOrder1 = em.merge(order1ListNewOrder1);
                    if (oldUserOfOrder1ListNewOrder1 != null && !oldUserOfOrder1ListNewOrder1.equals(user)) {
                        oldUserOfOrder1ListNewOrder1.getOrder1List().remove(order1ListNewOrder1);
                        oldUserOfOrder1ListNewOrder1 = em.merge(oldUserOfOrder1ListNewOrder1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cart> cartListOrphanCheck = user.getCartList();
            for (Cart cartListOrphanCheckCart : cartListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Cart " + cartListOrphanCheckCart + " in its cartList field has a non-nullable useruserId field.");
            }
            List<Order1> order1ListOrphanCheck = user.getOrder1List();
            for (Order1 order1ListOrphanCheckOrder1 : order1ListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Order1 " + order1ListOrphanCheckOrder1 + " in its order1List field has a non-nullable user field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
