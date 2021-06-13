package com.example.demo.persistance.entities;

import javax.persistence.*;
import javax.xml.registry.infomodel.User;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends UserApp{

    @Column(unique = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String neighborhood;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> petArrayList= new ArrayList<>();

    public Owner(){}

    public Owner(String username, String password, String email, String name, String address, String neighborhood) {
        super(username, password, email, "owner");
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public boolean addPet(Pet pet){
        try{
            petArrayList.add(pet);
            pet.setOwner(this);
            return true;
        }catch (Exception e){
            return false;
        }
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

    public List<Pet> getPetArrayList() {
        return petArrayList;
    }

    public void setPetArrayList(List<Pet> petArrayList) {
        this.petArrayList = petArrayList;
    }

}
