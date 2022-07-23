package com.example.phobo.Data.Api;

import com.example.phobo.Model.Booking;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BookingApi {
    @POST("booking")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Single<Booking> addBooking(@Body Booking booking);
}
