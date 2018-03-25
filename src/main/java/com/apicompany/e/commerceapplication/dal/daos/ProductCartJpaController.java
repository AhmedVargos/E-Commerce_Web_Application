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
import com.apicompany.e.commerceapplication.dal.entities.Cart;
import com.apicompany.e.commerceapplication.dal.entities.Product;
import com.apicompany.e.commerceapplication.dal.entities.ProductCart;
import com.apicompany.e.commerceapplication.dal.entities.ProductCartPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author szmoh
 */
public class ProductCartJpaController implements Serializable {

    public ProductCartJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductCart productCart) throws PreexistingEntityException, Exception {
        if (productCart.getProductCartPK() == null) {
            productCart.setProductCartPK(new ProductCartPK());
        }
        productCart.getProductCartPK().setProductproductId(productCart.getProduct().getProductPK().getProductId());
        productCart.getProductCartPK().setCartcartId(productCart.getCart().getCartId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cart cart = productCart.getCart();
            if (cart != null) {
                cart = em.getReference(cart.getClass(), cart.getCartId());
                productCart.setCart(cart);
            }
            Product product = productCart.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getProductPK());
                productCart.setProduct(product);
            }
            em.persist(productCart);
            if (cart != null) {
                cart.getProductCartList().add(productCart);
                cart = em.merge(cart);
            }
            if (product != null) {
                product.getProductCartList().add(productCart);
                product = em.merge(product);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductCart(productCart.getProductCartPK()) != null) {
                throw new PreexistingEntityException("ProductCart " + productCart + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductCart productCart) throws NonexistentEntityException, Exception {
        productCart.getProductCartPK().setProductproductId(productCart.getProduct().getProductPK().getProductId());
        productCart.getProductCartPK().setCartcartId(productCart.getCart().getCartId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductCart persistentProductCart = em.find(ProductCart.class, productCart.getProductCartPK());
            Cart cartOld = persistentProductCart.getCart();
            Cart cartNew = productCart.getCart();
            Product productOld = persistentProductCart.getProduct();
            Product productNew = productCart.getProduct();
            if (cartNew != null) {
                cartNew = em.getReference(cartNew.getClass(), cartNew.getCartId());
                productCart.setCart(cartNew);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getProductPK());
                productCart.setProduct(productNew);
            }
            productCart = em.merge(productCart);
            if (cartOld != null && !cartOld.equals(cartNew)) {
                cartOld.getProductCartList().remove(productCart);
                cartOld = em.merge(cartOld);
            }
            if (cartNew != null && !cartNew.equals(cartOld)) {
                cartNew.getProductCartList().add(productCart);
                cartNew = em.merge(cartNew);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getProductCartList().remove(productCart);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getProductCartList().add(productCart);
                productNew = em.merge(productNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductCartPK id = productCart.getProductCartPK();
                if (findProductCart(id) == null) {
                    throw new NonexistentEntityException("The productCart with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductCartPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductCart productCart;
            try {
                productCart = em.getReference(ProductCart.class, id);
                productCart.getProductCartPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productCart with id " + id + " no longer exists.", enfe);
            }
            Cart cart = productCart.getCart();
            if (cart != null) {
                cart.getProductCartList().remove(productCart);
                cart = em.merge(cart);
            }
            Product product = productCart.getProduct();
            if (product != null) {
                product.getProductCartList().remove(productCart);
                product = em.merge(product);
            }
            em.remove(productCart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductCart> findProductCartEntities() {
        return findProductCartEntities(true, -1, -1);
    }

    public List<ProductCart> findProductCartEntities(int maxResults, int firstResult) {
        return findProductCartEntities(false, maxResults, firstResult);
    }

    private List<ProductCart> findProductCartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductCart.class));
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

    public ProductCart findProductCart(ProductCartPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductCart.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductCart> rt = cq.from(ProductCart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
