package com.example.phobo.View.Fragment.HomeFragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {
    FragmentBookingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBookingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}