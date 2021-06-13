package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinary extends UserApp {

    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String neighborhood;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Visit.class)
    private List<Visit> visits= new ArrayList<>();

    public Veterinary(){}

    public Veterinary(String username, String password, String email, String name, String address, String neighborhood) {
        super(username, password, email, "veterinary");
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public boolean addVisit(Visit visit){
        return false;
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
