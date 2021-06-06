package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinary implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name= "id")
    private UserApp userApp;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String neighborhood;
    @OneToMany(mappedBy = "veterinary", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Visit.class)
    private List<Visit> visits= new ArrayList<>();

    public Veterinary(){}

    public Veterinary(UserApp userApp, String name, String address, String neighborhood) {
        this.userApp = userApp;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public boolean addVisit(Visit visit){
        try{
            visits.add(visit);
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }
}
