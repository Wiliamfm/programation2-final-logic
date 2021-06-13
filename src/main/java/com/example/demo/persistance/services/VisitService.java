package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.Veterinary;
import com.example.demo.persistance.entities.Visit;
import com.example.demo.persistance.entities.pojos.PetPOJO;
import com.example.demo.persistance.entities.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class VisitService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public VisitService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    /**
     * list all visits of the veterinary.
     * @param username the pk of veterinary.
     * @return List of pets POJO, null if the veterinary dont exists.
     */
    public List<VisitPOJO> list(String username){
        try {
            Veterinary veterinary= entityManager.find(Veterinary.class, username);
            List<Visit> visitList= veterinary.getVisits();
            List<VisitPOJO> visitPOJOS= new ArrayList<>();
            for (Visit visit :
                    visitList) {
                visitPOJOS.add(new VisitPOJO(visit.getId(), visit.getCreatedAt(), visit.getType(), visit.getDescription(), username, visit.getPet().getId()));
            }
            close();
            return visitPOJOS;
        }catch (Exception e){
            close();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * persist new visit in data base.
     * @param petId id of the per.
     * @param visit the visit to persist.
     * @param vetId id of the vet.
     * @return true if persisted, false other way.
     */
    public boolean create(Visit visit, int petId, String vetId){
        try{
            Pet pet = entityManager.find(Pet.class, petId);
            Veterinary veterinary= entityManager.find(Veterinary.class, vetId);
            if(pet!=null && veterinary!=null){
                entityManager.getTransaction().begin();
                pet.addVisit(visit);
                veterinary.addVisit(visit);
                entityManager.persist(visit);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                close();
                return false;
            }
        }catch (Exception e){
            System.out.println("Error while creating visit: " +e.getMessage());
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
