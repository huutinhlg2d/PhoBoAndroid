package com.example.phobo.View.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.model.Photographer;
import com.example.phobo.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    Photographer userDetail;
    FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", "onCreate: Da vo");
        if (getArguments() != null) {
            userDetail = (Photographer) getArguments().getSerializable("userDetail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(getLayoutInflater());
//        binding.setUserDetail(userDetail);
        return binding.getRoot();
    }
}