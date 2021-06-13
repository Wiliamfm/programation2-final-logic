package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.PetCase;

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

    /**
     * create new Case associated with the pet.
     * @param petCase Case to be persisted.
     * @param petId the id of the pet.
     * @return true if created, false other way.
     */
    public boolean create(int petId, PetCase petCase){
        try{
            Pet pet= entityManager.find(Pet.class, petId);
            if(pet!=null){
                entityManager.getTransaction().begin();
                pet.addCase(petCase);
                entityManager.persist(petCase);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Error while creating case: " +e.getMessage());
            close();
            return false;
        }
    }

    /**
     * close entities managers.
     */
    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
