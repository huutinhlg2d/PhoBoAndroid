package com.example.phobo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PhotographerConceptImage {
    @SerializedName("id")
    private int id;

    @SerializedName("photographerConcept")
    private PhotographerConcept photographerConcept;

    @SerializedName("imageUrl")
    private String imageUrl;
}
