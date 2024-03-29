package com.example.phobo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class Photographer extends User implements Serializable {
    @SerializedName("rate")
    float rate;

    @SerializedName("bookings")
    Set<Booking> bookings;

    @SerializedName("photographerConcepts")
    Set<PhotographerConcept> photographerConcepts;

    public Photographer(int id, String firebaseUid, String name, String email, String password, String avatarUrl, Date dateOfBirth, UserRole role, boolean isDeleted, float rate, Set<Booking> bookings, Set<PhotographerConcept> photographerConcepts) {
        super(id, firebaseUid, name, email, password, avatarUrl, dateOfBirth, role, isDeleted);
        this.rate = rate;
        this.bookings = bookings;
        this.photographerConcepts = photographerConcepts;
    }
    public Photographer(int id){
        super(id);
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<PhotographerConcept> getPhotographerConcepts() {
        return photographerConcepts;
    }

    public void setPhotographerConcepts(Set<PhotographerConcept> photographerConcepts) {
        this.photographerConcepts = photographerConcepts;
    }

    @Override
    public String toString() {
        return super.toString()+" Photographer{" +
                "rate=" + rate +
                ", bookings=" + bookings +
                ", photographerConcepts=" + photographerConcepts +
                '}';
    }
}
