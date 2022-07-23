package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

public class Concept implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("bookings")
    Set<Booking> bookings;

    public Concept(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
