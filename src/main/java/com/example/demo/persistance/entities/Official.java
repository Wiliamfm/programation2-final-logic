package com.example.demo.persistance.entities;

import javax.persistence.*;

@Entity
public class Official extends UserApp {

    @Column
    private String name;

    public Official() {}

    public Official(String username, String password, String email, String name) {
        super(username, password, email, "official");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
