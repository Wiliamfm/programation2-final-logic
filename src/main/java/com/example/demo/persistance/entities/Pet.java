package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String microship;
    @Column
    private String name;
    @Column
    private String specie;
    @Column
    private String race;
    @Column
    private String size;
    @Column
    private String sex;
    @Column
    private String picture;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Case.class)
    private List<Case> cases= new ArrayList<>();
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Visit.class)
    private List<Visit> visits= new ArrayList<>();

    public Pet(){}

    public Pet(String microship, String name, String specie, String race, String size, String sex, String picture) {
        this.microship = microship;
        this.name = name;
        this.specie = specie;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
    }

    public Pet(int id, String microship, String name, String specie, String race, String size, String sex, String picture, Owner owner) {
        this.id = id;
        this.microship = microship;
        this.name = name;
        this.specie = specie;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
        this.owner = owner;
    }

    public boolean addCase(Case cas){
        try{
            cases.add(cas);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addVisit(Visit visit){
        try{
            visits.add(visit);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMicroship() {
        return microship;
    }

    public void setMicroship(String microship) {
        this.microship = microship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}