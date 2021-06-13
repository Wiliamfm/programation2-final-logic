package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Official;
import com.example.demo.persistance.entities.UserApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OfficialService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public OfficialService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    /**
     * Create a official in the dataBase.
     * @param official object to persist.
     * @return true if persisted, false if doesnt.
     */
    public boolean create(Official official){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(official);
            entityManager.getTransaction().commit();
            close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            close();
            return false;
        }
    }

    /**
     * close the entiManager and entityManagerFactory
     */
    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
