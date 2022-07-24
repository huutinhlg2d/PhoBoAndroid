package com.example.phobo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.phobo.model.Photographer;
import com.example.phobo.model.User;
import com.example.phobo.ViewModel.UserApiService;
import com.example.phobo.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserApiService userApiService;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public User currentUser;
    public MenuItem navigation;
    public FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        drawerLayout = binding.myDrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentUser = (User) this.getIntent().getSerializableExtra("user");

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        Navigation.findNavController(binding.mainNavHostFragment).navigate(R.id.homeFragment);
                        break;
                    case R.id.nav_history:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", currentUser);
                        Navigation.findNavController(binding.mainNavHostFragment).navigate(R.id.historyFragment, bundle);
                        break;
                    case R.id.nav_logout:
                        Navigation.findNavController(binding.mainNavHostFragment).navigate(R.id.loginFragment2);
                        break;
                }
                binding.myDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.d("DEBUG", "onOptionsItemSelected: " + item.getItemId());
            navigation = item;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}