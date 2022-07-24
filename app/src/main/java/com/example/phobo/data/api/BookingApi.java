package com.example.phobo.data.api;

import com.example.phobo.model.Booking;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BookingApi {
    @POST("booking")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Single<Booking> addBooking(@Body Booking booking);
}
