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


    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public Booking(BookingState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public int getId() {
        return id;
    }

    public float getDuration() {
        return duration;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                ", photographer=" + photographer +
                ", concept=" + concept +
                ", bookingDate=" + bookingDate +
                ", bookingRate=" + bookingRate +
                ", duration=" + duration +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }
}
