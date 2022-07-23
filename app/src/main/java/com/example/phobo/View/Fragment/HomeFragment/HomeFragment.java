package com.example.phobo.View.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.Model.User;
import com.example.phobo.R;
import com.example.phobo.databinding.FragmentDetailBinding;
import com.example.phobo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = (User) getActivity().getIntent().getSerializableExtra("user");
        Log.d("test", "onCreate: "+ user.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.historyFragment, bundle);
    }
}