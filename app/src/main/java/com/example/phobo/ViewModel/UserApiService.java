package com.example.phobo.ViewModel;

import com.example.phobo.data.RetrofitInstance;
import com.example.phobo.data.api.UserApi;
import com.example.phobo.model.Photographer;
import com.example.phobo.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class UserApiService implements IApiService<User, Integer>{
    private UserApi userApi;

    public UserApiService(){
        userApi = RetrofitInstance.getRetrofitInsctance().create(UserApi.class);
    }

    @Override
    public Single<List<User>> getAll() {
        return userApi.getUsers();
    }

    public Single<List<Photographer>> getUsersRolePhotographer() {
        return  userApi.getUsersRolePhotographer();
    }

    @Override
    public Single<User> getById(Integer key) {
        return null;
    }

    @Override
    public Single<User> save(User entity) {
        return null;
    }

    @Override
    public void deleteById(Integer key) {

    }


}
