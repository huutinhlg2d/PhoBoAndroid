package com.example.phobo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @SerializedName("id")
    protected int id;

    @SerializedName("firebaseUid")
    private String firebaseUid;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("avatarUrl")
    private String avatarUrl;

    @SerializedName("dateOfBirth")
    private Date dateOfBirth;

    @SerializedName("role")
    private UserRole role;

    @SerializedName("isDeleted")
    private boolean isDeleted;

    public User(int id, String firebaseUid, String name, String email, String password, String avatarUrl, Date dateOfBirth, UserRole role, boolean isDeleted) {
        this.id = id;
        this.firebaseUid = firebaseUid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.isDeleted = isDeleted;
    }


    public User( String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email, String password, Date dateOfBirth, UserRole role, boolean isDeleted) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.isDeleted = isDeleted;
    }
    public User( String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firebaseUid=" + firebaseUid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", isDeleted=" + isDeleted +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
