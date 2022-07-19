package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class User {
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
}
