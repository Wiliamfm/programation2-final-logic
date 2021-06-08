package com.example.demo.persistance.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Official implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private UserApp userApp;
    @Column
    private String name;

    public Official() {}

    public Official(UserApp userApp, String name) {
        this.userApp = userApp;
        this.name = name;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
