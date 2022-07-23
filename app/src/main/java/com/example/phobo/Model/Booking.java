package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("photographer")
    private Photographer photographer;

    @SerializedName("concept")
    private Concept concept;

    @SerializedName("bookingDate")
    private Date bookingDate;

    @SerializedName("bookingRate")
    private float bookingRate;

    @SerializedName("duration")
    private float duration;

    @SerializedName("location")
    private String location;

    @SerializedName("note")
    private String note;

    @SerializedName("state")
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Booking(Customer customer, Photographer photographer, Concept concept, Date bookingDate, float duration, String location, String note) {
        this.customer = customer;
        this.photographer = photographer;
        this.concept = concept;
        this.bookingDate = bookingDate;
        this.duration = duration;
        this.location = location;
        this.note = note;
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
                ", location='" + location + '\'' +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }
}
