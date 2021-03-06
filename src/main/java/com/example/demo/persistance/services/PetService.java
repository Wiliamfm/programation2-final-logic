package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.pojos.PetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PetService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PetService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    /**
     * list all pets of the owner.
     * @param username the pk of owner.
     * @return List of pets POJO, null if the owner dont exists.
     */
    public List<PetPOJO> list(String username){
        try {
            Owner owner= entityManager.find(Owner.class, username);
            List<Pet> petList= owner.getPetArrayList();
            List<PetPOJO> petPOJOS= new ArrayList<>();
            for (Pet pet :
                    petList) {
                String micro= pet.getMicroship();
                if(micro==null){
                    micro= "No registra";
                }
                petPOJOS.add(new PetPOJO(pet.getId(), micro, pet.getName(), pet.getSpecie(), pet.getRace(), pet.getSize(), pet.getSex(), pet.getPicture(), pet.getOwner().getUsername()));
            }
            close();
            return petPOJOS;
        }catch (Exception e){
            close();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Persist pet in the data base.
     * @param username the username owner of the pets.
     * @param pet object to persist.
     * @return true if persisted, false if doesnt.
     */
    public boolean create(String username, Pet pet){
        try{
            Owner owner= entityManager.find(Owner.class, username);
            if(owner!=null){
                entityManager.getTransaction().begin();
                owner.addPet(pet);
                entityManager.persist(pet);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                close();
                return false;
            }
        }catch (Exception e){
            System.out.println("Error while creating Pet: " +e.getMessage());
            close();
            return false;
        }
    }

    public boolean modify(int petId, String name, String microship, String specie, String race, String size, String sex, String picture){
        try{
            Pet pet= entityManager.find(Pet.class, petId);
            if( pet!=null){
                entityManager.getTransaction().begin();
                pet.setName(name);
                pet.setMicroship(microship);
                pet.setSpecie(specie);
                pet.setRace(race);
                pet.setSize(size);
                pet.setSex(sex);
                pet.setPicture(picture);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                close();
                return false;
            }
        }catch (Exception e){
            close();
            e.printStackTrace();
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
