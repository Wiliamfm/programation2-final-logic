package com.example.demo.persistance.entities.pojos;

import java.util.ArrayList;

public class UserAppPOJO {

    private String username;
    private String password;
    private String email;
    private String role;
    private ArrayList<OfficialPOJO> officialPOJOS= new ArrayList<>();
    private ArrayList<OwnerPOJO> ownerPOJOS= new ArrayList<>();
    private ArrayList<VeterinaryPOJO> veterinaryPOJOS= new ArrayList<>();

    public UserAppPOJO(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public ArrayList<OfficialPOJO> getOfficialPOJOS() {
        return officialPOJOS;
    }

    public void setOfficialPOJOS(ArrayList<OfficialPOJO> officialPOJOS) {
        this.officialPOJOS = officialPOJOS;
    }

    public ArrayList<OwnerPOJO> getOwnerPOJOS() {
        return ownerPOJOS;
    }

    public void setOwnerPOJOS(ArrayList<OwnerPOJO> ownerPOJOS) {
        this.ownerPOJOS = ownerPOJOS;
    }

    public ArrayList<VeterinaryPOJO> getVeterinaryPOJOS() {
        return veterinaryPOJOS;
    }

    public void setVeterinaryPOJOS(ArrayList<VeterinaryPOJO> veterinaryPOJOS) {
        this.veterinaryPOJOS = veterinaryPOJOS;
    }
}
