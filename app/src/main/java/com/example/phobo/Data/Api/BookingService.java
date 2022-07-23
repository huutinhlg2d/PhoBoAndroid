package com.example.phobo.Data.Api;

import com.example.phobo.Model.Booking;
import com.example.phobo.Model.Customer;
import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
