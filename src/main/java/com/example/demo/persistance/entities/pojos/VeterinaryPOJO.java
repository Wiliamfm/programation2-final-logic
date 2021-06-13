package com.example.demo.persistance.entities.pojos;

import java.util.ArrayList;

public class VeterinaryPOJO {

    private String username;
    private String password;
    private String email;
    private String role;
    private String name;
    private String address;
    private String neighborhood;
    private ArrayList<VisitPOJO> visitPOJOS= new ArrayList<>();

    public VeterinaryPOJO(String username, String password, String email, String role, String name, String address, String neighborhood) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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
