package com.example.phobo.model;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {

    private int id;

    private Customer customer;

    private Photographer photographer;

    private Concept concept;

    private Date bookingDate;

    private float bookingRate;

    private float duration;

    private String note;

    private BookingState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public float getBookingRate() {
        return bookingRate;
    }

    public void setBookingRate(float bookingRate) {
        this.bookingRate = bookingRate;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
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
