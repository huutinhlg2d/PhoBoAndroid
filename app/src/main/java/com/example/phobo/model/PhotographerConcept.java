package com.example.phobo.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class PhotographerConcept {
    @SerializedName("id")
    private int id;

    @SerializedName("photographer")
    private Photographer photographer;

    @SerializedName("concept")
    private Concept concept;

    @SerializedName("durationConfig")
    private String durationConfig;

    @SerializedName("photographerConceptImages")
    private Set<PhotographerConceptImage> photographerConceptImages;

    public PhotographerConcept(int id, Photographer photographer, Concept concept, String durationConfig, Set<PhotographerConceptImage> photographerConceptImages) {
        this.id = id;
        this.photographer = photographer;
        this.concept = concept;
        this.durationConfig = durationConfig;
        this.photographerConceptImages = photographerConceptImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDurationConfig() {
        return durationConfig;
    }

    public void setDurationConfig(String durationConfig) {
        this.durationConfig = durationConfig;
    }

    public Set<PhotographerConceptImage> getPhotographerConceptImages() {
        return photographerConceptImages;
    }

    public void setPhotographerConceptImages(Set<PhotographerConceptImage> photographerConceptImages) {
        this.photographerConceptImages = photographerConceptImages;
    }

    @Override
    public String toString() {
        return "PhotographerConcept{" +
                "id=" + id +
                ", photographer=" + photographer +
                ", concept=" + concept +
                ", durationConfig='" + durationConfig + '\'' +
                ", photographerConceptImages=" + photographerConceptImages +
                '}';
    }
}
