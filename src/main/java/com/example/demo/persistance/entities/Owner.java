package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private UserApp userApp;
    @Column(unique = true)
    @GeneratedValue
    private int personId;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String neighborhood;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pet> petArrayList= new ArrayList<>();

    public Owner(){}

    public Owner(UserApp userApp, int personId, String name, String address, String neighborhood) {
        this.userApp = userApp;
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public Owner(UserApp userApp, int personId, String name, String address, String neighborhood, ArrayList<Pet> petArrayList) {
        this.userApp = userApp;
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.petArrayList = petArrayList;
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

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
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

    public void setPetArrayList(ArrayList<Pet> petArrayList) {
        this.petArrayList = petArrayList;
    }
}
