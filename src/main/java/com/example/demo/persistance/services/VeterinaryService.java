package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.UserApp;
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

    /**
     * Persist veterinary in the data base.
     * @param veterinary object to persist.
     * @return true if persisted, false if doesnt.
     */
    public boolean create(Veterinary veterinary){
        try{
                entityManager.getTransaction().begin();
                entityManager.persist(veterinary);
                entityManager.getTransaction().commit();
                close();
                return true;
        }catch (Exception e){
            System.out.println("Error while creating veterinary: " +e.getMessage());
            close();
            return false;
        }
    }

    /**
     * modify the veterinary info.
     * @param username pk of veterinary.
     * @param address address to modify.
     * @param neighborhood neighborhood to modify
     * @return true if modify, false other way.
     */
    public boolean modify(String username, String address, String neighborhood){
        try {
            entityManager.getTransaction().begin();
            Veterinary veterinary= entityManager.find(Veterinary.class, username);
            if(veterinary!=null){
                veterinary.setAddress(address);
                veterinary.setNeighborhood(neighborhood);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                close();
                return false;
            }
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
