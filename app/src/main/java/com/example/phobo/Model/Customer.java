package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Customer extends User implements Serializable {
    @SerializedName("bookings")
    List<Booking> bookings;

    public Customer(int id, String firebaseUid, String name, String email, String password, String avatarUrl, Date dateOfBirth, UserRole role, boolean isDeleted, List<Booking> bookings) {
        super(id, firebaseUid, name, email, password, avatarUrl, dateOfBirth, role, isDeleted);
        this.bookings = bookings;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Customer(int id) {
        super(id);
    }
}
