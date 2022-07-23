package com.example.phobo.View.Fragment.LoginFragment;

import android.content.Context;
import android.content.Intent;
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
import com.example.phobo.MainActivity;
import com.example.phobo.model.User;
import com.example.phobo.databinding.FragmentLoginBinding;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    Retrofit retrofit;
    String session=null;
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
        binding = FragmentLoginBinding.inflate(inflater);
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
        loadSession();
    }
    private void loadSession(){
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        session=sharedPreferences.getString("email","");
        if(session!=null){
            loadUserFromSession();
        }
    }

    private void loadUserFromSession(){
        User user = new User(binding.loginEmail.getText().toString());
        retrofit.create(UserApi.class).loadUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<User> apiUser) {
//                                Log.d("123", "onSuccess: "+apiUser.get(0).getEmail()+apiUser.get(0).getPassword());
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("user", apiUser.get(0));
                        startActivity(intent);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("123", "onFail: "+ e.getMessage());
                    }
                });
    }
    private void login(){
        User user = new User(binding.loginEmail.getText().toString(),binding.password.getText().toString());
        retrofit.create(UserApi.class).login(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull User apiUser) {
//                                Log.d("123", "onSuccess: "+apiUser.get(0).getEmail()+apiUser.get(0).getPassword());
                        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", apiUser.getEmail());
                        editor.commit();
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("user", apiUser);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("123", "onFail: "+ e.getMessage());
                    }
                });
    }

    private void setLoginEvent(){
        binding.signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
}