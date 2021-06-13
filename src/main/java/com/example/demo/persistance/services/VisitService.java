package com.example.demo.persistance.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VisitService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public VisitService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public boolean create(){
        try{
            return true;
        }catch (Exception e){
            System.out.println("Error while creating visit: " +e.getMessage());
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
