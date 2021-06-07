package com.example.demo.persistance.entities.pojos;

import java.util.ArrayList;

public class OwnerPOJO {

    private String id;
    private int personId;
    private String name;
    private String addres;
    private String neighborhood;
    private ArrayList<PetPOJO> petPOJOS= new ArrayList<PetPOJO>();

    public OwnerPOJO(String id, int personId, String name, String addres, String neighborhood) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.addres = addres;
        this.neighborhood = neighborhood;
    }

    public void addPet(PetPOJO petPOJO){
        petPOJOS.add(petPOJO);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public ArrayList<PetPOJO> getPetPOJOS() {
        return petPOJOS;
    }

    public void setPetPOJOS(ArrayList<PetPOJO> petPOJOS) {
        this.petPOJOS = petPOJOS;
    }
}
