package com.example.phobo.View.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phobo.Data.Api.BookingService;
import com.example.phobo.Data.Api.UserApi;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.Booking;
import com.example.phobo.Model.BookingState;
import com.example.phobo.Model.User;
import com.example.phobo.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BookingAdapter  extends  RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    Context context;
    List<Booking> bookingList;
    User user;
    BookingService bookingService;

    public BookingAdapter(Context context, List<Booking> choiceItemList,User user) {
        this.context = context;
        this.bookingList = choiceItemList;
        this.user=user;
        bookingService= RetrofitInstance.getRetrofitInsctance().create(BookingService.class);
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("!23", "onCreateViewHolder: "+ user.getRole().toString());
        if(user.getRole().toString().equals("CUSTOMER")){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customer_booking_item, parent, false);
            return new ViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.photographer_booking_item, parent, false);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.state.setText(booking.getState().toString());
        if(user.getRole().toString().equals("CUSTOMER")){
            holder.name.setText(" Photographer" +booking.getPhotographer().getName()+"\n"+" Duration : " + String.valueOf(booking.getDuration())+"\n Concept"+booking.getConcept().getName());
        }
        else{
            holder.name.setText(" Customer"+booking.getCustomer().getName()+"\n"+" Duration : " + String.valueOf(booking.getDuration())+"\n Concept "+booking.getConcept().getName());
        }

        setFabByState(booking,holder);
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView state;
        TextView name;
        ExtendedFloatingActionButton cancelBtn;
        ExtendedFloatingActionButton acceptBtn;
        ExtendedFloatingActionButton declineBtn;
        public ViewHolder(View view) {
            super(view);
            state=view.findViewById(R.id.state_tv_bookingitem);
            name=view.findViewById(R.id.name_tv_bookingitem);
            cancelBtn=view.findViewById(R.id.cancel);
            acceptBtn=view.findViewById(R.id.accept);
            declineBtn=view.findViewById(R.id.decline);
        }
    }

    private void setFabByState(Booking booking,@NonNull BookingAdapter.ViewHolder holder){
        if(booking.getState().toString().equals("WAITING")){
            if(user.getRole().toString().equals("CUSTOMER")){
                holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancel(booking,holder);
                    }
                });
            }
            else{
                holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accept(booking,holder);
                    }
                });
                holder.declineBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        decline(booking,holder);
                    }
                });
            }

        }
        else{
            Log.d("!23", "onCreateViewHolder: "+ booking.getState());
            if(user.getRole().toString().equals("CUSTOMER")){
            holder.cancelBtn.setVisibility(View.GONE);
            }
            else{

                holder.acceptBtn.setVisibility(View.GONE);
                holder.declineBtn.setVisibility(View.GONE);
            }
        }
    }

    private void cancel(Booking myBooking,@NonNull BookingAdapter.ViewHolder holder){
        bookingService.cancelBooking(myBooking.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Booking>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Booking booking) {
                        myBooking.setState(BookingState.CANCELED);
                        holder.cancelBtn.setVisibility(View.GONE);
                        holder.state.setText(myBooking.getState().toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("user", "error: " + t.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("user", "success:"+ user );
                    }
                });
    }
    private void accept(Booking myBooking,@NonNull BookingAdapter.ViewHolder holder){
        bookingService.acceptBooking(myBooking.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Booking>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Booking booking) {
                        myBooking.setState(BookingState.ACCEPTED);
                        holder.acceptBtn.setVisibility(View.GONE);
                        holder.declineBtn.setVisibility(View.GONE);
                        holder.state.setText(myBooking.getState().toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("user", "error: " + t.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("user", "success:"+ user );
                    }
                });
    }
    private void decline(Booking myBooking,@NonNull BookingAdapter.ViewHolder holder){
        bookingService.declineBooking(myBooking.getId())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Booking>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Booking booking) {
                        myBooking.setState(BookingState.DECLINED);
                        holder.declineBtn.setVisibility(View.GONE);
                        holder.acceptBtn.setVisibility(View.GONE);
                        holder.state.setText(myBooking.getState().toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("user", "error: " + t.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("user", "success:"+ user );
                    }
                });
    }
}
