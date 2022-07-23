package com.example.phobo.View.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.phobo.model.Concept;
import com.example.phobo.model.Photographer;
import com.example.phobo.databinding.FragmentDetailBinding;
import com.example.phobo.model.PhotographerConcept;
import com.example.phobo.model.UserRole;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        binding.setUserDetail(userDetail);
        if (userDetail.getRole() == UserRole.PENDINGPHOTOGRAPHER){
            binding.btnBook.setTextIsSelectable(false);
        }
        binding.txtName.setText("Name: "+userDetail.getName());
        binding.txtRate.setText("Rate: "+userDetail.getRate());
        binding.txtRole.setText("Role: "+userDetail.getRole());

        ArrayList<String> names = new ArrayList<String>();;
        for (PhotographerConcept concept: userDetail.getPhotographerConcepts()
             ) {
            String temp = concept.getConcept() != null ? concept.getConcept().getName() : "";
            names.add(temp);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, names);
        binding.lvPhotographerConcept.setAdapter(adapter );
        Picasso.get().load(userDetail.getAvatarUrl()).into(binding.ivPhotographer);
        return binding.getRoot();
    }
}