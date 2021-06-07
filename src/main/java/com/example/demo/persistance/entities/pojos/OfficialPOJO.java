package com.example.demo.persistance.entities.pojos;

public class OfficialPOJO {

    private String username;
    private String name;

    public OfficialPOJO(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
