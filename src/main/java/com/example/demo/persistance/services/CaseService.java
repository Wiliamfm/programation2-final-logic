package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Case;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CaseService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CaseService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public boolean create(Case cas){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cas);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating case: " +e.getMessage());
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
