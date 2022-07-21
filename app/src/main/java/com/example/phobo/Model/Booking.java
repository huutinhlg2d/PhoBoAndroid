package com.example.phobo.Model;

import java.util.Date;

public class Booking {

    private int id;

    private Customer customer;

    private Photographer photographer;

    private Concept concept;

    private Date bookingDate;

    private float bookingRate;

    private float duration;

    private String note;

    private BookingState state;
}
