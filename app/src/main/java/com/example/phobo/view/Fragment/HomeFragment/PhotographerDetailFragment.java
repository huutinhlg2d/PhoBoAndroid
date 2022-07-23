package com.example.phobo.view.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.databinding.FragmentHomeBinding;
import com.example.phobo.databinding.FragmentPhotographerDetailBinding;

public class PhotographerDetailFragment extends Fragment {

    FragmentPhotographerDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotographerDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}