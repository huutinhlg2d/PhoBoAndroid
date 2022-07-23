package com.example.phobo.view.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.databinding.FragmentConceptConfigBinding;

public class ConceptConfigFragment extends Fragment {

    FragmentConceptConfigBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConceptConfigBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}