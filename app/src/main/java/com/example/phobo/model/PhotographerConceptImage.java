package com.example.phobo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotographerConceptImage implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("photographerConcept")
    private PhotographerConcept photographerConcept;

    @SerializedName("imageUrl")
    private String imageUrl;
}
