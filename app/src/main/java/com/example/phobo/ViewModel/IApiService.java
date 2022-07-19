package com.example.phobo.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IApiService<T,K> {
    Single<List<T>> getAll();

    Single<T> getById(K key);

    Single<T> save(T entity);

    void deleteById(K key);
}
