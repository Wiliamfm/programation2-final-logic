package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.repositories.OwnerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class OwnerService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public OwnerService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public List<OwnerPOJO> listOwners(){
        try{
            return null;
        }catch (Exception e){
            System.out.println("Error while listing owners: " +e.getMessage());
            return null;
        }

    }

    public Owner getOwner(String username){
        try{
            return entityManager.find(Owner.class, username);
        }catch (Exception e){
            close();
            System.out.println("Error getting owner: " +e.getMessage());
            return null;
        }
    }

    /**
     * Persist owner in the data base.
     * @param owner object to persist.
     * @return true if persisted, false if doesnt.
     */
    public boolean create(Owner owner){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            close();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating owner: " +e.getMessage());
            close();
            return false;
        }
    }

    /**
     * modify the owner info.
     * @param username pk of owner.
     * @param address address to modify.
     * @param neighborhood neighborhood to modify
     * @return true if modify, false other way.
     */
    public boolean modify(String username, String address, String neighborhood){
        try{
            entityManager.getTransaction().begin();
            Owner owner= entityManager.find(Owner.class, username);
            if(owner!=null){
                owner.setAddress(address);
                owner.setNeighborhood(neighborhood);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else {
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
