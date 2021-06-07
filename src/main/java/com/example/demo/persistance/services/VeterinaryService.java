package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Veterinary;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VeterinaryService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public VeterinaryService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public boolean create(Veterinary veterinary){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(veterinary);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating veterinary: " +e.getMessage());
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
