package com.example.phobo.Data.Api;

import com.example.phobo.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    @GET("user/all")
    Single<List<User>> getUsers();


    @POST("/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Single<List<User>> login(@Body User user);

    @POST("/register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Single<List<User>> register(@Body User user);

    @POST("/user/load")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Single<List<User>> loadUser(@Body User user);
}
