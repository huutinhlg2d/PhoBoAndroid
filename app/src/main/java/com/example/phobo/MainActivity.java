package com.example.phobo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.User;
import com.example.phobo.View.Fragment.HomeFragment.HistoryFragment;
import com.example.phobo.ViewModel.UserApiService;
import com.example.phobo.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserApiService userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityMainBinding.inflate(getLayoutInflater());
         View view = binding.getRoot();
         setContentView(view);

//        Fragment fragment = new HistoryFragment();
//        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainerView, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();


//        navController.navigate(R.id.historyFragment);

//         userApiService = new UserApiService();
//
//        userApiService.getUsersRolePhotographer().subscribeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<List<Photographer>>() {
//                    @Override
//                    public void onSuccess(@NonNull List<Photographer> users) {
//                        Log.d("DEBUG", "start");
//                        for (Photographer user: users) {
//                            Log.d("DEBUG", "get");
//                            Log.d("LIST", user.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d("ERROR", e.getMessage());
//                    }
//                });
    }
}