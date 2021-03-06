package com.example.demo.persistance.entities.pojos;

import java.util.ArrayList;

public class OwnerPOJO {

    private String username;
    private String password;
    private String email;
    private String role;
    private int personId;
    private String name;
    private String address;
    private String neighborhood;
    private ArrayList<PetPOJO> petPOJOS= new ArrayList<>();
    private int totalPets;

    public OwnerPOJO(String username, String password, String email, String role, int personId, String name, String address, String neighborhood) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public void setPetPOJOS(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getTotalPets() {
        return totalPets;
    }

    public void setTotalPets(int totalPets) {
        this.totalPets = totalPets;
    }
}
