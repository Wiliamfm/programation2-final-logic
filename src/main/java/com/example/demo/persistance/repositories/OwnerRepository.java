package com.example.demo.persistance.repositories;

import com.example.demo.persistance.entities.Owner;

import javax.persistence.EntityManager;
import java.util.List;

public class OwnerRepository {
    private EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager){
        this.entityManager= entityManager;
    }

    public List<Owner> listOwners(){
        try{
            List<Owner> owners= entityManager.createQuery("from owner").getResultList();
            return owners;
        }catch (Exception e){
            System.out.println("Error while listing owners: " +e.getMessage());
            return null;
        }

    }

    public boolean create(Owner owner){
        try{
            entityManager.persist(owner);
            return true;
        }catch (Exception e){
            System.out.println("Error while creating owner: " +e.getMessage());
            return false;
        }
    }
}
