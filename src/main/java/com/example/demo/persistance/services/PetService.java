package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Pet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PetService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PetService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public boolean create(Pet pet){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(pet);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating Pet: " +e.getMessage());
            return false;
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
