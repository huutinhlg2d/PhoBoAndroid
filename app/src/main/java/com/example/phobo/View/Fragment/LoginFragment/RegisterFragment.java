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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.phobo.Data.Api.UserApi;
import com.example.phobo.Data.RetrofitInstance;
import com.example.phobo.Model.User;
import com.example.phobo.Model.UserRole;
import com.example.phobo.databinding.FragmentRegisterBinding;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
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
        super.onViewCreated(view, savedInstanceState);
        setup();
    }

    private void setup(){
        retrofit= RetrofitInstance.getRetrofitInsctance();
        setRegisterFab();
    }
    private void setRegisterFab(){
        binding.registerFabRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = getUserFromUI();
                if(user!=null){
                    register(user);
                }
                else {
                    Toast.makeText(getActivity(), "password repeat incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private User getUserFromUI(){
        String name=binding.nameEdtRegister.getText().toString();
        String email=binding.emailEdtRegister.getText().toString();
        String password=binding.passwordEdtRegister.getText().toString();
        String passwordRepeat =binding.passwordrepeatEdtRegister.getText().toString();
        Date date=getDateFromDateTimePicker();
        String role=getRoleFromRadioGroup();
        if(password.equals(passwordRepeat)){
            return new User(name,email,password,date,UserRole.valueOf(role),false);
        }
        return null;
    }


    private Date getDateFromDateTimePicker(){
        long dateTime = binding.dayofbirthDpRegister.getCalendarView().getDate();
        Date date = new Date(dateTime);
        return date;
    }
    private String getRoleFromRadioGroup(){
        int selectedId = binding.roleRgrpRegister.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) getView().findViewById(selectedId);
        return radioButton.getText().toString();
    }
    private void register(User user){
        retrofit.create(UserApi.class).register(user)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        Log.d("user", "onNext: " +  string);
                        Toast.makeText(getActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("user", "error: " + t.getLocalizedMessage());
                        Toast.makeText(getActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("user", "success:"+ user );
                    }
                });
    }
}