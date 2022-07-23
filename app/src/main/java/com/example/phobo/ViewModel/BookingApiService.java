package com.example.phobo.ViewModel;

import com.example.phobo.Data.Api.BookingApi;
import com.example.phobo.Data.Api.UserApi;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.Booking;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class BookingApiService implements IApiService<Booking, Integer>{
    private BookingApi bookingApi;

    public BookingApiService(){
        bookingApi = RetrofitInstance.getRetrofitInsctance().create(BookingApi.class);
    }

    @Override
    public Single<List<Booking>> getAll() {
        return null;
    }

    @Override
    public Single<Booking> getById(Integer key) {
        return null;
    }

    @Override
    public Single<Booking> save(Booking entity) {
        return bookingApi.addBooking(entity);
    }

    @Override
    public void deleteById(Integer key) {

    }
}
