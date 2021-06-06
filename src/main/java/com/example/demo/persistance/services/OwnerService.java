package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OwnerService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public OwnerService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public boolean create(Owner owner){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating owner: " +e.getMessage());
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
