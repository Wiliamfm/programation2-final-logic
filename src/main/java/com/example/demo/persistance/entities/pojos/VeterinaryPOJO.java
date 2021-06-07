package com.example.demo.persistance.entities.pojos;

import java.util.ArrayList;

public class VeterinaryPOJO {

    private String username;
    private String name;
    private String address;
    private String neighborhood;
    private ArrayList<VisitPOJO> visitPOJOS= new ArrayList<>();

    public VeterinaryPOJO(String username, String name, String address, String neighborhood) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ArrayList<VisitPOJO> getVisitPOJOS() {
        return visitPOJOS;
    }

    public void setVisitPOJOS(ArrayList<VisitPOJO> visitPOJOS) {
        this.visitPOJOS = visitPOJOS;
    }
}
