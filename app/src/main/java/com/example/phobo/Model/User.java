package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class User implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("firebaseUid")
    private int firebaseUid;

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

    public User( String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }
}
