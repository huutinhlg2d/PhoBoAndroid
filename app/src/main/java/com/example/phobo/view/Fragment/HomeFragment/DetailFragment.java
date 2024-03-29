package com.example.phobo.view.Fragment.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.phobo.R;
import com.example.phobo.databinding.FragmentDetailBinding;
import com.example.phobo.model.Photographer;
import com.example.phobo.model.PhotographerConcept;
import com.example.phobo.model.User;
import com.example.phobo.model.UserRole;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    Photographer currentPhotographer;
    FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", "onCreate: Da vo");
        if (getArguments() != null) {
            currentPhotographer = (Photographer) getArguments().getSerializable("userDetail");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailBinding.inflate(getLayoutInflater());
        binding.setUserDetail(currentPhotographer);
        User user  = (User)getActivity().getIntent().getSerializableExtra("user");
        if (UserRole.PHOTOGRAPHER == user.getRole()){
            binding.btnBook.setVisibility(View.GONE);
        }else{
            binding.btnBook.setVisibility(View.VISIBLE);
        }
        binding.txtRate.setText("Rate: "+ currentPhotographer.getRate());

        binding.txtName.setText("Name: "+ currentPhotographer.getName());
        binding.txtRole.setText("Role: "+ currentPhotographer.getRole());

        binding.btnBook.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("photographer", currentPhotographer);
            Navigation.findNavController(getView()).navigate(R.id.bookingFragment, bundle);
        });

        ArrayList<String> names = new ArrayList<String>();;
        for (PhotographerConcept concept : currentPhotographer.getPhotographerConcepts()) {
            String temp = concept.getConcept() != null ? concept.getConcept().getName() : "";
            names.add(temp);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, names);
        binding.lvPhotographerConcept.setAdapter(adapter );
        Picasso.get().load(currentPhotographer.getAvatarUrl()).into(binding.ivPhotographer);
        return binding.getRoot();
    }
}