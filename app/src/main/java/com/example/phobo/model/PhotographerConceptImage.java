package com.example.phobo.model;

import com.google.gson.annotations.SerializedName;

public class PhotographerConceptImage {
    @SerializedName("id")
    private int id;

    @SerializedName("photographerConcept")
    private PhotographerConcept photographerConcept;

    @SerializedName("imageUrl")
    private String imageUrl;
}
