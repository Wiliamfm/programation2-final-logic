package com.example.demo.persistance.entities.pojos;


import java.util.ArrayList;

public class PetPOJO {

    private int id;
    private String microship;
    private String name;
    private String specie;
    private String race;
    private String size;
    private String sex;
    private String picture;
    private int ownerId;
    private ArrayList<CasePOJO> casePOJOS= new ArrayList<>();
    private ArrayList<VisitPOJO> visitPOJOS= new ArrayList<>();

    public PetPOJO(int id, String microship, String name, String specie, String race, String size, String sex, String picture, int ownerId) {
        this.id = id;
        this.microship = microship;
        this.name = name;
        this.specie = specie;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public ArrayList<CasePOJO> getCasePOJOS() {
        return casePOJOS;
    }

    public void setCasePOJOS(ArrayList<CasePOJO> casePOJOS) {
        this.casePOJOS = casePOJOS;
    }

    public ArrayList<VisitPOJO> getVisitPOJOS() {
        return visitPOJOS;
    }

    public void setVisitPOJOS(ArrayList<VisitPOJO> visitPOJOS) {
        this.visitPOJOS = visitPOJOS;
    }
}
