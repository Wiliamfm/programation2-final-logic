package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Official;
import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.OfficialPOJO;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.entities.pojos.PetPOJO;

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
     * get an official.
     * @param username id of the official.
     * @return the owner in a pojo.
     */
    public OfficialPOJO getOfficial(String username){
        try{
            Official official= entityManager.find(Official.class, username);
            if(official!=null){
                OfficialPOJO officialPOJO= new OfficialPOJO(official.getUsername(), official.getPassword(), official.getEmail(), official.getRole(), official.getName());
                close();
                return officialPOJO;
            }else {
                close();
                return null;
            }
        }catch (Exception e){
            close();
            System.out.println("Error getting owner: " +e.getMessage());
            return null;
        }
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
