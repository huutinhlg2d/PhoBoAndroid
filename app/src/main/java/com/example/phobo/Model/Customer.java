package com.example.phobo.Model;

import java.util.Date;
import java.util.List;

public class Customer extends User{
    List<Booking> bookings;

    public Customer(int id, String firebaseUid, String name, String email, String password, String avatarUrl, Date dateOfBirth, UserRole role, boolean isDeleted, List<Booking> bookings) {
        super(id, firebaseUid, name, email, password, avatarUrl, dateOfBirth, role, isDeleted);
        this.bookings = bookings;
    }

    public Customer(int id) {
        super(id);
    }
}
