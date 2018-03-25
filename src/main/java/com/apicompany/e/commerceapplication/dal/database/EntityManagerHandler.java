package com.apicompany.e.commerceapplication.dal.database;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHandler {
    private static EntityManagerHandler entityManagerHandler = null;
    private EntityManager entityManager;
    private EntityManagerFactory factory;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    private EntityManagerHandler() {
        factory = Persistence.createEntityManagerFactory("EcommercePU");
        entityManager = factory.createEntityManager();
    }


    public static EntityManagerHandler getEntityManagerHandler() {
        if (entityManagerHandler == null) {
            synchronized (DatabaseHandler.class) {
                if (entityManagerHandler == null) {
                    entityManagerHandler = new EntityManagerHandler();
                }
            }
        }
        return entityManagerHandler;
    }
}
