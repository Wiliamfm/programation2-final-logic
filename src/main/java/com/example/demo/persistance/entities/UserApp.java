package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserApp {

    @Id
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String role;
    @OneToMany(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Owner.class)
    private List<Owner> ownerArrayList= new ArrayList<>();
    @OneToMany(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Official.class)
    private List<Official> officialArrayList= new ArrayList<>();
    @OneToMany(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Veterinary.class)
    private List<Veterinary> veterinaryArrayList= new ArrayList<>();

    public UserApp(){}

    public UserApp(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public boolean addOwner(Owner owner){
        try {
            ownerArrayList.add(owner);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addOfficial(Official official){
        try {
            officialArrayList.add(official);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addVeterinary(Veterinary veterinary){
        try {
            veterinaryArrayList.add(veterinary);
            return true;
        }catch (Exception e){
            return false;
        }
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

    public List<Owner> getOwnerArrayList() {
        return ownerArrayList;
    }

    public void setOwnerArrayList(ArrayList<Owner> ownerArrayList) {
        this.ownerArrayList = ownerArrayList;
    }

    public List<Official> getOfficialArrayList() {
        return officialArrayList;
    }

    public void setOfficialArrayList(ArrayList<Official> officialArrayList) {
        this.officialArrayList = officialArrayList;
    }

    public List<Veterinary> getVeterinaryArrayList() {
        return veterinaryArrayList;
    }

    public void setVeterinaryArrayList(ArrayList<Veterinary> veterinaryArrayList) {
        this.veterinaryArrayList = veterinaryArrayList;
    }
}
