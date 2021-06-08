package com.example.demo.persistance.entities.pojos;


public class UserAppPOJO {

    private String username;
    private String password;
    private String email;
    private String role;
    private OwnerPOJO ownerPOJO;
    private OfficialPOJO officialPOJO;
    private VeterinaryPOJO veterinaryPOJO;

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

    public OwnerPOJO getOwnerPOJO() {
        return ownerPOJO;
    }

    public void setOwnerPOJO(OwnerPOJO ownerPOJO) {
        this.ownerPOJO = ownerPOJO;
    }

    public OfficialPOJO getOfficialPOJO() {
        return officialPOJO;
    }

    public void setOfficialPOJO(OfficialPOJO officialPOJO) {
        this.officialPOJO = officialPOJO;
    }

    public VeterinaryPOJO getVeterinaryPOJO() {
        return veterinaryPOJO;
    }

    public void setVeterinaryPOJO(VeterinaryPOJO veterinaryPOJO) {
        this.veterinaryPOJO = veterinaryPOJO;
    }
}
