package com.example.phobo.view.Fragment.LoginFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.data.api.UserApi;
import com.example.phobo.data.RetrofitInstance;
import com.example.phobo.MainActivity;
import com.example.phobo.R;
import com.example.phobo.databinding.FragmentLoginBinding;
import com.example.phobo.model.User;
import com.example.phobo.model.UserRole;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    UserApi userApi;
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
        userApi= RetrofitInstance.getRetrofitInsctance().create(UserApi.class);
        setLoginEvent();
        loadSession();
        setRegisterBtnEvent();
    }
    private void loadSession(){
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        if(sharedPreferences.getString("email","").equals("")==false){
            User user= loadUserFromSession(sharedPreferences);
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
        }
    }
    private User loadUserFromSession(SharedPreferences sharedPreferences){
        int id=sharedPreferences.getInt("id",0);
        String firebaseUid=sharedPreferences.getString("firebaseUid","");
        String email =sharedPreferences.getString("email","");
        String password =sharedPreferences.getString("password","");
        String name =sharedPreferences.getString("name","");
        String avatarUrl =sharedPreferences.getString("avatarUrl","");
        UserRole role = UserRole.valueOf( sharedPreferences.getString("role",""));
        boolean isDeleted= sharedPreferences.getBoolean("isDeleted",false);
        return new User(id,firebaseUid,name,email,password,avatarUrl,new GregorianCalendar(2014, Calendar.APRIL,11).getTime(),role,isDeleted);
    }

    private void login(){
        User user = new User(binding.emailEdtLogin.getText().toString(),binding.passwordEdtLogin.getText().toString());
        userApi.login(user)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        saveSessionUser(user);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("user", "error: " + t.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setLoginEvent(){
        binding.loginFabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void setRegisterBtnEvent(){
        binding.registerFabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment register = new RegisterFragment();
                loadFragment(register);

            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void saveSessionUser(User user){
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasUser",true);
        editor.putString("email",user.getEmail());
        editor.putString("password",user.getPassword());
        editor.putString("name",user.getName());
        editor.putInt("id",user.getId());
        editor.putString("avatarUrl",user.getAvatarUrl());
        editor.putString("firebaseUid",user.getFirebaseUid());
        editor.putString("role",user.getRole().toString());
        editor.putBoolean("isDeleted",user.isDeleted());
        editor.commit();
    }
}