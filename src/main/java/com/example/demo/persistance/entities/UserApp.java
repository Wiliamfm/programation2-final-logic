package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserApp implements Serializable {

    @Id
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String role;
    @OneToOne(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Owner owner= new Owner();
    @OneToOne(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Official official= new Official();
    @OneToOne(mappedBy = "userApp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Veterinary veterinary= new Veterinary();

    public UserApp(){}

    public UserApp(String username, String password, String email, String role) {
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Official getOfficial() {
        return official;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    public Veterinary getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Veterinary veterinary) {
        this.veterinary = veterinary;
    }
}
