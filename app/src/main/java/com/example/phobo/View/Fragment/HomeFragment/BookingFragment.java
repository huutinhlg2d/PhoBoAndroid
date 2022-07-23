package com.example.phobo.View.Fragment.HomeFragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.MainActivity;
import com.example.phobo.Model.Booking;
import com.example.phobo.Model.Concept;
import com.example.phobo.Model.Customer;
import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.PhotographerConcept;
import com.example.phobo.Model.User;
import com.example.phobo.View.Adapter.ConceptSpinnerAdapter;
import com.example.phobo.ViewModel.PhotographerApiService;
import com.example.phobo.databinding.FragmentBookingBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BookingFragment extends Fragment {
    private FragmentBookingBinding binding;
    private Photographer photographer;
    private User currentUser;
    private PhotographerApiService photographerApiService;
    private ConceptSpinnerAdapter conceptSpinnerAdapter;
    private List<Concept> concepts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photographer = (Photographer) getArguments().getSerializable("photographer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBookingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentUser = (User) ((MainActivity) getActivity()).getIntent().getSerializableExtra("user");

        photographerApiService = new PhotographerApiService();

        photographerApiService.getPhotographerConcept(photographer.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PhotographerConcept>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<PhotographerConcept> photographerConcepts) {
                        List<Concept> c = photographerConcepts.stream().map(pc -> pc.getConcept()).collect(Collectors.toList());
                        concepts.addAll(c);
                        conceptSpinnerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("ERROR", "onError:" + e.getMessage());
                    }
                });
        conceptSpinnerAdapter = new ConceptSpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, concepts);
        binding.spnConceptBooking.setAdapter(conceptSpinnerAdapter);

        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Booking getBooking(){
        Concept concept = (Concept) binding.spnConceptBooking.getSelectedItem();
        Customer customer = new Customer(currentUser.getId());
        Photographer photographer = new Photographer(this.photographer.getId());

    }
}