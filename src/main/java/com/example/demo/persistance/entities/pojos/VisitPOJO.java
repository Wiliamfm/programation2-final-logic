package com.example.demo.persistance.entities.pojos;

import java.util.Date;

public class VisitPOJO {

    private int id;
    private String createdAt;
    private String type;
    private String description;
    private String vetId;
    private int petId;

    public VisitPOJO(int id, String createdAt, String type, String description, String vetId, int petId) {
        this.id = id;
        this.createdAt = createdAt;
        this.type = type;
        this.description = description;
        this.vetId = vetId;
        this.petId = petId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVetId() {
        return vetId;
    }

    public void setVetId(String vetId) {
        this.vetId = vetId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
