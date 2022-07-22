package com.example.phobo.Data.Api;

import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface UserApi {
    @GET("user/all")
    Single<List<User>> getUsers();

    @GET("user/photographer/all")
    Single<List<Photographer>> getUsersRolePhotographer();
}
