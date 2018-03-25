/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.daos;

import com.apicompany.e.commerceapplication.dal.daos.exceptions.IllegalOrphanException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.NonexistentEntityException;
import com.apicompany.e.commerceapplication.dal.entities.Cart;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.apicompany.e.commerceapplication.dal.entities.User;
import com.apicompany.e.commerceapplication.dal.entities.ProductCart;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author szmoh
 */
public class CartJpaController implements Serializable {

    public CartJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cart cart) {
        if (cart.getProductCartList() == null) {
            cart.setProductCartList(new ArrayList<ProductCart>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User useruserId = cart.getUseruserId();
            if (useruserId != null) {
                useruserId = em.getReference(useruserId.getClass(), useruserId.getUserId());
                cart.setUseruserId(useruserId);
            }
            List<ProductCart> attachedProductCartList = new ArrayList<ProductCart>();
            for (ProductCart productCartListProductCartToAttach : cart.getProductCartList()) {
                productCartListProductCartToAttach = em.getReference(productCartListProductCartToAttach.getClass(), productCartListProductCartToAttach.getProductCartPK());
                attachedProductCartList.add(productCartListProductCartToAttach);
            }
            cart.setProductCartList(attachedProductCartList);
            em.persist(cart);
            if (useruserId != null) {
                useruserId.getCartList().add(cart);
                useruserId = em.merge(useruserId);
            }
            for (ProductCart productCartListProductCart : cart.getProductCartList()) {
                Cart oldCartOfProductCartListProductCart = productCartListProductCart.getCart();
                productCartListProductCart.setCart(cart);
                productCartListProductCart = em.merge(productCartListProductCart);
                if (oldCartOfProductCartListProductCart != null) {
                    oldCartOfProductCartListProductCart.getProductCartList().remove(productCartListProductCart);
                    oldCartOfProductCartListProductCart = em.merge(oldCartOfProductCartListProductCart);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cart cart) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cart persistentCart = em.find(Cart.class, cart.getCartId());
            User useruserIdOld = persistentCart.getUseruserId();
            User useruserIdNew = cart.getUseruserId();
            List<ProductCart> productCartListOld = persistentCart.getProductCartList();
            List<ProductCart> productCartListNew = cart.getProductCartList();
            List<String> illegalOrphanMessages = null;
            for (ProductCart productCartListOldProductCart : productCartListOld) {
                if (!productCartListNew.contains(productCartListOldProductCart)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductCart " + productCartListOldProductCart + " since its cart field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (useruserIdNew != null) {
                useruserIdNew = em.getReference(useruserIdNew.getClass(), useruserIdNew.getUserId());
                cart.setUseruserId(useruserIdNew);
            }
            List<ProductCart> attachedProductCartListNew = new ArrayList<ProductCart>();
            for (ProductCart productCartListNewProductCartToAttach : productCartListNew) {
                productCartListNewProductCartToAttach = em.getReference(productCartListNewProductCartToAttach.getClass(), productCartListNewProductCartToAttach.getProductCartPK());
                attachedProductCartListNew.add(productCartListNewProductCartToAttach);
            }
            productCartListNew = attachedProductCartListNew;
            cart.setProductCartList(productCartListNew);
            cart = em.merge(cart);
            if (useruserIdOld != null && !useruserIdOld.equals(useruserIdNew)) {
                useruserIdOld.getCartList().remove(cart);
                useruserIdOld = em.merge(useruserIdOld);
            }
            if (useruserIdNew != null && !useruserIdNew.equals(useruserIdOld)) {
                useruserIdNew.getCartList().add(cart);
                useruserIdNew = em.merge(useruserIdNew);
            }
            for (ProductCart productCartListNewProductCart : productCartListNew) {
                if (!productCartListOld.contains(productCartListNewProductCart)) {
                    Cart oldCartOfProductCartListNewProductCart = productCartListNewProductCart.getCart();
                    productCartListNewProductCart.setCart(cart);
                    productCartListNewProductCart = em.merge(productCartListNewProductCart);
                    if (oldCartOfProductCartListNewProductCart != null && !oldCartOfProductCartListNewProductCart.equals(cart)) {
                        oldCartOfProductCartListNewProductCart.getProductCartList().remove(productCartListNewProductCart);
                        oldCartOfProductCartListNewProductCart = em.merge(oldCartOfProductCartListNewProductCart);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cart.getCartId();
                if (findCart(id) == null) {
                    throw new NonexistentEntityException("The cart with id " + id + " no longer exists.");
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
            Cart cart;
            try {
                cart = em.getReference(Cart.class, id);
                cart.getCartId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cart with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductCart> productCartListOrphanCheck = cart.getProductCartList();
            for (ProductCart productCartListOrphanCheckProductCart : productCartListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cart (" + cart + ") cannot be destroyed since the ProductCart " + productCartListOrphanCheckProductCart + " in its productCartList field has a non-nullable cart field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User useruserId = cart.getUseruserId();
            if (useruserId != null) {
                useruserId.getCartList().remove(cart);
                useruserId = em.merge(useruserId);
            }
            em.remove(cart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cart> findCartEntities() {
        return findCartEntities(true, -1, -1);
    }

    public List<Cart> findCartEntities(int maxResults, int firstResult) {
        return findCartEntities(false, maxResults, firstResult);
    }

    private List<Cart> findCartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cart.class));
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

    public Cart findCart(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cart.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cart> rt = cq.from(Cart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
