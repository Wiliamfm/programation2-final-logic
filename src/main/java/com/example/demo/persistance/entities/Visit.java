package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Visit implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String createdAt;
    @Column
    private String type;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
    @ManyToOne
    @JoinColumn(name = "vetId")
    private Veterinary veterinary;

    public Visit(){}

    public Visit(int id, String createdAt, String type, String description, Pet pet, Veterinary veterinary) {
        this.id = id;
        this.createdAt = createdAt;
        this.type = type;
        this.description = description;
        this.pet = pet;
        this.veterinary = veterinary;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Veterinary getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Veterinary veterinary) {
        this.veterinary = veterinary;
    }
}
