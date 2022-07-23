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
}
