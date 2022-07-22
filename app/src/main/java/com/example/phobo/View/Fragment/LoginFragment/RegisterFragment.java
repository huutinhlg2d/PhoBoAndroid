package com.example.phobo.View.Fragment.LoginFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.Data.Api.UserApi;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.User;
import com.example.phobo.Model.UserRole;
import com.example.phobo.databinding.FragmentRegisterBinding;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setup();
    }

    private void setup(){
        retrofit= RetrofitInstance.getRetrofitInsctance();
        setLoginEvent();
    }

    private void register(){
        User user = new User(binding.registerName.getText().toString()
                ,binding.registerEmail.getText().toString(),binding.registerPassword.getText().toString(),
                new Date(binding.registerBirthday.getCalendarView().getDate()), UserRole.CUSTOMER,false
        );
        retrofit.create(UserApi.class).register(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<User> apiUser) {
//                                Log.d("123", "onSuccess: "+apiUser.get(0).getEmail()+apiUser.get(0).getPassword());
                        getActivity().onBackPressed();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("123", "onFail: "+ e.getMessage());
                    }
                });
    }

    private void setLoginEvent(){
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
}