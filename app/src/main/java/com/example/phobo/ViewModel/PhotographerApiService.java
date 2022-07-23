package com.example.phobo.ViewModel;

import com.example.phobo.Data.Api.PhotographerApi;
import com.example.phobo.Data.Api.UserApi;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.PhotographerConcept;
import com.example.phobo.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class PhotographerApiService implements IApiService<Photographer, Integer>{
    private PhotographerApi photographerApi;

    public PhotographerApiService(){
        photographerApi = RetrofitInstance.getRetrofitInsctance().create(PhotographerApi.class);
    }

    @Override
    public Single<List<Photographer>> getAll() {
        return null;
    }

    @Override
    public Single<Photographer> getById(Integer key) {
        return null;
    }

    @Override
    public Single<Photographer> save(Photographer entity) {
        return null;
    }

    @Override
    public void deleteById(Integer key) {

    }

    public Single<List<PhotographerConcept>> getPhotographerConcept(Integer key) {
        return photographerApi.getPhotographerConcepts(key);
    }
}
