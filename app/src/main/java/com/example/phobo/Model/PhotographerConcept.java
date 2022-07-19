package com.example.phobo.Model;

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
}
