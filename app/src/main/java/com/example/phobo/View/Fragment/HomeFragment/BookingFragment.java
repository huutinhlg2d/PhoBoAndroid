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
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.phobo.MainActivity;
import com.example.phobo.Model.Booking;
import com.example.phobo.Model.Concept;
import com.example.phobo.Model.Customer;
import com.example.phobo.Model.Photographer;
import com.example.phobo.Model.PhotographerConcept;
import com.example.phobo.Model.User;
import com.example.phobo.View.Adapter.ConceptSpinnerAdapter;
import com.example.phobo.ViewModel.BookingApiService;
import com.example.phobo.ViewModel.PhotographerApiService;
import com.example.phobo.databinding.FragmentBookingBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private BookingApiService bookingApiService;
    private ConceptSpinnerAdapter conceptSpinnerAdapter;
    private List<PhotographerConcept> photographerConcepts = new ArrayList<>();

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
        bookingApiService = new BookingApiService();

        conceptSpinnerAdapter = new ConceptSpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, photographerConcepts);
        binding.spnConceptBooking.setAdapter(conceptSpinnerAdapter);

        photographerApiService.getPhotographerConcept(photographer.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PhotographerConcept>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<PhotographerConcept> photographerConcepts) {
                        BookingFragment.this.photographerConcepts.addAll(photographerConcepts);
                        conceptSpinnerAdapter.notifyDataSetChanged();
                        binding.spnConceptBooking.setSelection(0);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("ERROR", "onError:" + e.getMessage());
                    }
                });

        binding.spnConceptBooking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.rgDuration.removeAllViews();
                PhotographerConcept photographerConcepts = (PhotographerConcept) binding.spnConceptBooking.getSelectedItem();
                String[] durations = getDuration(photographerConcepts.getDurationConfig());
                for (String duration:
                     durations) {
                    RadioButton radioButton = new RadioButton(getContext());
                    radioButton.setText(duration);
                    binding.rgDuration.addView(radioButton);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "onClick: " + getBooking());
                bookingApiService.save(getBooking())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Booking>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Booking booking) {
                                Log.d("DEBUG", "onSuccess: " + booking);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d("EROR", "onFail: "+ e.getMessage());
                            }
                        });
            }
        });
    }

    private Booking getBooking(){
        Concept concept =  ((PhotographerConcept)binding.spnConceptBooking.getSelectedItem()).getConcept();
        Customer customer = new Customer(currentUser.getId());
        Photographer photographer = new Photographer(this.photographer.getId());
        Date bookingDate = getDate();
        String location = binding.txtLocation.getText().toString();
        String note = binding.txtNote.getText().toString();
        float duration = getCheckedDuration();

        return new Booking(customer, photographer, concept, bookingDate, duration, location, note);
    }

    private Date getDate(){
        DatePicker datePicker = binding.datePicker;
        TimePicker timePicker = binding.timePicker;

        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());

        Log.d("DEBUG", "getDate: " + timePicker.getCurrentHour() + " " + timePicker.getCurrentMinute());

        long time = calendar.getTimeInMillis();

        return new Date(time);
    }

    private String[] getDuration(String durationConfig){
        return durationConfig.split(":", 0);
    }

    private float getCheckedDuration(){
        int checkedRadioButtonId = binding.rgDuration.getCheckedRadioButtonId();
        RadioButton radioButton = binding.rgDuration.findViewById(checkedRadioButtonId);
        return Float.parseFloat(radioButton.getText().toString());
    }
}