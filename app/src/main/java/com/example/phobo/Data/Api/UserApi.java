package com.example.phobo.Data.Api;

import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    @GET("user/all")
    Single<List<User>> getUsers();

    @GET("user/photographer/all")
    Single<List<Photographer>> getUsersRolePhotographer();


    @POST("user/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})

    Observable<User> login(@Body User user);
    @POST("user/register")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<String> register(@Body User user);

}
