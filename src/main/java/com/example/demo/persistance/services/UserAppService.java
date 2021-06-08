package com.example.demo.persistance.services;

import com.example.demo.persistance.entities.UserApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserAppService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserAppService(){
        entityManagerFactory= Persistence.createEntityManagerFactory("db");
        entityManager= entityManagerFactory.createEntityManager();
    }

    public List<UserApp> listUsers(){
        try{
            entityManager.getTransaction().begin();
            List<UserApp> userApps= entityManager.createQuery("from UserApp").getResultList();
            return userApps;
        }catch (Exception e){
            System.out.println("Error while listing users: " +e.getMessage());
            return null;
        }

    }

    public boolean create(UserApp userApp){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(userApp);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println("Error while creating user: " +e.getMessage());
            return false;
        }
    }

    public String validateUser(String username, String password){
        try{
            UserApp userApp= entityManager.find(UserApp.class, username);
            if(userApp!=null){
                if(userApp.getUsername().equals(username) && userApp.getPassword().equals(password)){
                    return userApp.getRole();
                }else{
                    return "no match";
                }
            }else{
                return "not found";
            }
        }catch (Exception e){
            return "error";
        }
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
