package com.example.phobo.ViewModel;

import com.example.phobo.data.api.PhotographerApi;
import com.example.phobo.data.api.UserApi;
import com.example.phobo.data.RetrofitInstance;
import com.example.phobo.model.Photographer;
import com.example.phobo.model.PhotographerConcept;
import com.example.phobo.model.User;

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
