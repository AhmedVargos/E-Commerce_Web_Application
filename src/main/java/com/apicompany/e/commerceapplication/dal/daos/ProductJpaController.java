/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.dal.daos;

import com.apicompany.e.commerceapplication.dal.daos.exceptions.IllegalOrphanException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.NonexistentEntityException;
import com.apicompany.e.commerceapplication.dal.daos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.apicompany.e.commerceapplication.dal.entities.Category;
import com.apicompany.e.commerceapplication.dal.entities.Product;
import com.apicompany.e.commerceapplication.dal.entities.ProductCart;
import java.util.ArrayList;
import java.util.List;
import com.apicompany.e.commerceapplication.dal.entities.ProductOrder;
import com.apicompany.e.commerceapplication.dal.entities.ProductPK;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author szmoh
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) throws PreexistingEntityException, Exception {
        if (product.getProductPK() == null) {
            product.setProductPK(new ProductPK());
        }
        if (product.getProductCartList() == null) {
            product.setProductCartList(new ArrayList<ProductCart>());
        }
        if (product.getProductOrderList() == null) {
            product.setProductOrderList(new ArrayList<ProductOrder>());
        }
        product.getProductPK().setCategorycategoryId(product.getCategory().getCategoryId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category category = product.getCategory();
            if (category != null) {
                category = em.getReference(category.getClass(), category.getCategoryId());
                product.setCategory(category);
            }
            List<ProductCart> attachedProductCartList = new ArrayList<ProductCart>();
            for (ProductCart productCartListProductCartToAttach : product.getProductCartList()) {
                productCartListProductCartToAttach = em.getReference(productCartListProductCartToAttach.getClass(), productCartListProductCartToAttach.getProductCartPK());
                attachedProductCartList.add(productCartListProductCartToAttach);
            }
            product.setProductCartList(attachedProductCartList);
            List<ProductOrder> attachedProductOrderList = new ArrayList<ProductOrder>();
            for (ProductOrder productOrderListProductOrderToAttach : product.getProductOrderList()) {
                productOrderListProductOrderToAttach = em.getReference(productOrderListProductOrderToAttach.getClass(), productOrderListProductOrderToAttach.getProductOrderPK());
                attachedProductOrderList.add(productOrderListProductOrderToAttach);
            }
            product.setProductOrderList(attachedProductOrderList);
            em.persist(product);
            if (category != null) {
                category.getProductList().add(product);
                category = em.merge(category);
            }
            for (ProductCart productCartListProductCart : product.getProductCartList()) {
                Product oldProductOfProductCartListProductCart = productCartListProductCart.getProduct();
                productCartListProductCart.setProduct(product);
                productCartListProductCart = em.merge(productCartListProductCart);
                if (oldProductOfProductCartListProductCart != null) {
                    oldProductOfProductCartListProductCart.getProductCartList().remove(productCartListProductCart);
                    oldProductOfProductCartListProductCart = em.merge(oldProductOfProductCartListProductCart);
                }
            }
            for (ProductOrder productOrderListProductOrder : product.getProductOrderList()) {
                Product oldProductOfProductOrderListProductOrder = productOrderListProductOrder.getProduct();
                productOrderListProductOrder.setProduct(product);
                productOrderListProductOrder = em.merge(productOrderListProductOrder);
                if (oldProductOfProductOrderListProductOrder != null) {
                    oldProductOfProductOrderListProductOrder.getProductOrderList().remove(productOrderListProductOrder);
                    oldProductOfProductOrderListProductOrder = em.merge(oldProductOfProductOrderListProductOrder);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProduct(product.getProductPK()) != null) {
                throw new PreexistingEntityException("Product " + product + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        product.getProductPK().setCategorycategoryId(product.getCategory().getCategoryId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getProductPK());
            Category categoryOld = persistentProduct.getCategory();
            Category categoryNew = product.getCategory();
            List<ProductCart> productCartListOld = persistentProduct.getProductCartList();
            List<ProductCart> productCartListNew = product.getProductCartList();
            List<ProductOrder> productOrderListOld = persistentProduct.getProductOrderList();
            List<ProductOrder> productOrderListNew = product.getProductOrderList();
            List<String> illegalOrphanMessages = null;
            for (ProductCart productCartListOldProductCart : productCartListOld) {
                if (!productCartListNew.contains(productCartListOldProductCart)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductCart " + productCartListOldProductCart + " since its product field is not nullable.");
                }
            }
            for (ProductOrder productOrderListOldProductOrder : productOrderListOld) {
                if (!productOrderListNew.contains(productOrderListOldProductOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductOrder " + productOrderListOldProductOrder + " since its product field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoryNew != null) {
                categoryNew = em.getReference(categoryNew.getClass(), categoryNew.getCategoryId());
                product.setCategory(categoryNew);
            }
            List<ProductCart> attachedProductCartListNew = new ArrayList<ProductCart>();
            for (ProductCart productCartListNewProductCartToAttach : productCartListNew) {
                productCartListNewProductCartToAttach = em.getReference(productCartListNewProductCartToAttach.getClass(), productCartListNewProductCartToAttach.getProductCartPK());
                attachedProductCartListNew.add(productCartListNewProductCartToAttach);
            }
            productCartListNew = attachedProductCartListNew;
            product.setProductCartList(productCartListNew);
            List<ProductOrder> attachedProductOrderListNew = new ArrayList<ProductOrder>();
            for (ProductOrder productOrderListNewProductOrderToAttach : productOrderListNew) {
                productOrderListNewProductOrderToAttach = em.getReference(productOrderListNewProductOrderToAttach.getClass(), productOrderListNewProductOrderToAttach.getProductOrderPK());
                attachedProductOrderListNew.add(productOrderListNewProductOrderToAttach);
            }
            productOrderListNew = attachedProductOrderListNew;
            product.setProductOrderList(productOrderListNew);
            product = em.merge(product);
            if (categoryOld != null && !categoryOld.equals(categoryNew)) {
                categoryOld.getProductList().remove(product);
                categoryOld = em.merge(categoryOld);
            }
            if (categoryNew != null && !categoryNew.equals(categoryOld)) {
                categoryNew.getProductList().add(product);
                categoryNew = em.merge(categoryNew);
            }
            for (ProductCart productCartListNewProductCart : productCartListNew) {
                if (!productCartListOld.contains(productCartListNewProductCart)) {
                    Product oldProductOfProductCartListNewProductCart = productCartListNewProductCart.getProduct();
                    productCartListNewProductCart.setProduct(product);
                    productCartListNewProductCart = em.merge(productCartListNewProductCart);
                    if (oldProductOfProductCartListNewProductCart != null && !oldProductOfProductCartListNewProductCart.equals(product)) {
                        oldProductOfProductCartListNewProductCart.getProductCartList().remove(productCartListNewProductCart);
                        oldProductOfProductCartListNewProductCart = em.merge(oldProductOfProductCartListNewProductCart);
                    }
                }
            }
            for (ProductOrder productOrderListNewProductOrder : productOrderListNew) {
                if (!productOrderListOld.contains(productOrderListNewProductOrder)) {
                    Product oldProductOfProductOrderListNewProductOrder = productOrderListNewProductOrder.getProduct();
                    productOrderListNewProductOrder.setProduct(product);
                    productOrderListNewProductOrder = em.merge(productOrderListNewProductOrder);
                    if (oldProductOfProductOrderListNewProductOrder != null && !oldProductOfProductOrderListNewProductOrder.equals(product)) {
                        oldProductOfProductOrderListNewProductOrder.getProductOrderList().remove(productOrderListNewProductOrder);
                        oldProductOfProductOrderListNewProductOrder = em.merge(oldProductOfProductOrderListNewProductOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductPK id = product.getProductPK();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getProductPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductCart> productCartListOrphanCheck = product.getProductCartList();
            for (ProductCart productCartListOrphanCheckProductCart : productCartListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the ProductCart " + productCartListOrphanCheckProductCart + " in its productCartList field has a non-nullable product field.");
            }
            List<ProductOrder> productOrderListOrphanCheck = product.getProductOrderList();
            for (ProductOrder productOrderListOrphanCheckProductOrder : productOrderListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the ProductOrder " + productOrderListOrphanCheckProductOrder + " in its productOrderList field has a non-nullable product field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Category category = product.getCategory();
            if (category != null) {
                category.getProductList().remove(product);
                category = em.merge(category);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(ProductPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
