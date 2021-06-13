package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.Owner;
import com.example.demo.persistance.entities.Pet;
import com.example.demo.persistance.entities.PetCase;
import com.example.demo.persistance.entities.UserApp;
import com.example.demo.persistance.entities.pojos.CasePOJO;
import com.example.demo.persistance.entities.pojos.OwnerPOJO;
import com.example.demo.persistance.entities.pojos.PetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UserAppService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserAppService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public UserApp validateUser(String username, String password){
        try{
            UserApp userApp= entityManager.find(UserApp.class, username);
            if(userApp!=null){
                if(userApp.getUsername().equals(username) && userApp.getPassword().equals(password)){
                    close();
                    return userApp;
                }else{
                    close();
                    return null;
                }
            }else{
                close();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            close();
            return null;
        }
    }

    public List<PetPOJO> getPets(){
        try{
            List<Pet> pets= entityManager.createQuery("from Pet").getResultList();
            if(pets!=null){
                List<PetPOJO> petPOJOS= new ArrayList<>();
                for (Pet pet: pets) {
                    petPOJOS.add(new PetPOJO(pet.getId(), pet.getMicroship(), pet.getName(), pet.getSpecie(), pet.getRace(), pet.getSize(), pet.getSex(), pet.getPicture(), pet.getOwner().getUsername()));
                }
                close();
                return petPOJOS;
            }else{
                close();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            close();
            return null;
        }
    }

    public List<OwnerPOJO> getOwners(){
        try{
            List<Owner> owners= entityManager.createQuery("from Owner").getResultList();
            if(owners!=null){
                List<OwnerPOJO> ownerPOJOS= new ArrayList<>();
                for (Owner owner: owners) {
                    int totalPets= owner.getPetArrayList().size();
                    OwnerPOJO ownerPOJO=new OwnerPOJO(owner.getUsername(), owner.getPassword(), owner.getEmail(), owner.getRole(), owner.getPersonId(), owner.getName(), owner.getAddress(), owner.getNeighborhood());
                    ownerPOJO.setTotalPets(totalPets);
                    ownerPOJOS.add(ownerPOJO);
                }
                close();
                return ownerPOJOS;
            }else{
                close();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            close();
            return null;
        }
    }

    public List<CasePOJO> getCases(){
        try{
            List<PetCase> petCase= entityManager.createQuery("from PetCase ").getResultList();
            if(petCase!=null){
                List<CasePOJO> casePOJOS= new ArrayList<>();
                for (PetCase petCase1: petCase) {
                    casePOJOS.add(new CasePOJO(petCase1.getId(), petCase1.getCreatedAt(), petCase1.getType(), petCase1.getDescription(), petCase1.getPet().getId()));
                }
                close();
                return casePOJOS;
            }else{
                close();
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            close();
            return null;
        }
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
