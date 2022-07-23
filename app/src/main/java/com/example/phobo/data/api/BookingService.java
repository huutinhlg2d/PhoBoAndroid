package com.example.phobo.data.api;

import com.example.phobo.model.Booking;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookingService {

    @GET("booking/customer/{id}")
    Single<List<Booking>> getCustomerListBooking(@Path("id") int id);

    @GET("booking/photographer/{id}")
    Single<List<Booking>> getPhotorapherListBooking(@Path("id") int id);

    @PUT("booking/accept/{id}")
    Observable<Booking> acceptBooking(@Path("id") int id);

    @PUT("booking/cancel/{id}")
    Observable<Booking> cancelBooking(@Path("id") int id);

    @PUT("booking/decline/{id}")
    Observable<Booking> declineBooking(@Path("id") int id);
}
