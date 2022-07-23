package com.example.phobo.View.Fragment.HomeFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.Data.Api.BookingService;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.Booking;
import com.example.phobo.Model.BookingState;
import com.example.phobo.Model.Customer;
import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.User;
import com.example.phobo.Model.UserRole;
import com.example.phobo.View.Adapter.BookingAdapter;
import com.example.phobo.databinding.FragmentDetailBinding;
import com.example.phobo.databinding.FragmentHistoryBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    List<Booking> bookingList;
    BookingAdapter bookingAdapter;
    BookingService bookingService;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("user");
        }
        else {
            user= loadUserFromSession();
        }
        Log.d("DEBUG", "onCreate: " + user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
    }
    void setup(){
        bookingService= RetrofitInstance.getRetrofitInsctance().create(BookingService.class);
        setListBooking();
        loadListBookingFromApi();
    }
    void setListBooking(){
        bookingList = new ArrayList<>();
//        bookingList.add(new Booking(BookingState.ACCEPTED));
//        bookingList.add(new Booking(BookingState.DECLINED));
//        bookingList.add(new Booking(BookingState.WAITING));
        bookingAdapter = new BookingAdapter(getContext(),bookingList,user);
        binding.bookinglistRcvBooking.setAdapter(bookingAdapter);
        binding.bookinglistRcvBooking.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    void loadListBookingFromApi(){
        if(user.getRole().toString().equals("CUSTOMER")){
            getCustomerListBooking();
        }
        else{
            getPhotographerListBooking();
        }
    }
    private void getCustomerListBooking(){
        bookingService.getCustomerListBooking(user.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Booking>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Booking> listBook) {
                        Log.d("123", "onSuccess: "+ listBook);
                        bookingList.clear();
                        bookingList.addAll(listBook);
                        bookingAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("error", "onError: " + e.getMessage());
                    }
                });
    }
    private void getPhotographerListBooking(){
        bookingService.getPhotorapherListBooking(user.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Booking>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Booking> listBook) {
                        bookingList.clear();
                        bookingList.addAll(listBook);
                        bookingAdapter.notifyDataSetChanged();
                        Log.d("succes", "onSucces: ");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("error", "onError: " + e.getMessage());
                    }
                });
    }
    private User loadUserFromSession(){
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int id=sharedPreferences.getInt("id",0);
        String firebaseUid=sharedPreferences.getString("firebaseUid","");
        String email =sharedPreferences.getString("email","");
        String password =sharedPreferences.getString("password","");
        String name =sharedPreferences.getString("name","");
        String avatarUrl =sharedPreferences.getString("avatarUrl","");
        UserRole role =UserRole.valueOf( sharedPreferences.getString("role",""));
        boolean isDeleted= sharedPreferences.getBoolean("isDeleted",false);
        return new User(id,firebaseUid,name,email,password,avatarUrl,new GregorianCalendar(2014, Calendar.APRIL,11).getTime(),role,isDeleted);
    }
}