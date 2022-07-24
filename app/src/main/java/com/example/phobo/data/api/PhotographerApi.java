package com.example.phobo.data.api;

import com.example.phobo.model.PhotographerConcept;
import com.example.phobo.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PhotographerApi {
    @GET("photographer/concept/{id}")
    Single<List<PhotographerConcept>> getPhotographerConcepts(@Path("id") Integer id);
}
