package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;
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
            entityManager.getTransaction().begin();
            List<Owner> owners= entityManager.createQuery("from Owner").getResultList();
            List<OwnerPOJO> ownerPOJOS= new ArrayList<>();
            for (Owner owner :
                    owners) {
                ownerPOJOS.add(new OwnerPOJO(owner.getUserApp().getUsername(), owner.getPersonId(), owner.getName(), owner.getAddress(), owner.getNeighborhood()));
            }
            return ownerPOJOS;
        }catch (Exception e){
            System.out.println("Error while listing owners: " +e.getMessage());
            return null;
        }

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
        }
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
