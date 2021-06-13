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

    public UserApp getUserApp(String userName){
        try{
            return entityManager.find(UserApp.class, userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean create(UserApp userApp){
        try{
            if(userApp.getRole().equals("owner")||userApp.getRole().equals("official")||userApp.getRole().equals("veterinary")){
                entityManager.getTransaction().begin();
                entityManager.persist(userApp);
                entityManager.getTransaction().commit();
                close();
                return true;
            }else{
                close();
                return false;
            }
        }catch (Exception e){
            System.out.println("Error while creating user: " +e.getMessage());
            close();
            return false;
        }
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

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
