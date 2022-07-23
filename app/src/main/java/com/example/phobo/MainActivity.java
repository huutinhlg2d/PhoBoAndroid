package com.example.phobo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.phobo.ViewModel.UserApiService;
import com.example.phobo.databinding.ActivityMainBinding;

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