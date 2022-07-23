package com.example.phobo.view.Fragment.HomeFragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phobo.model.Photographer;
import com.example.phobo.view.Adapter.MyPhotographerListRecyclerViewAdapter;
import com.example.phobo.R;
import com.example.phobo.ViewModel.UserApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A fragment representing a list of Items.
 */
public class PhotographerListFragment extends Fragment {
    private UserApiService userApiService;
    private List<Photographer> users = new ArrayList<>();
    private MyPhotographerListRecyclerViewAdapter adapter;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotographerListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PhotographerListFragment newInstance(int columnCount) {
        PhotographerListFragment fragment = new PhotographerListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            userApiService = new UserApiService();

            userApiService.getUsersRolePhotographer().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<Photographer>>() {
//                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onSuccess(@NonNull List<Photographer> usersT) {
                            Log.d("DEBUG", "start");
                            users.addAll(usersT);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("ERROR", e.getMessage());
                        }
                    });

            //int id, String firebaseUid, String name, String email, String password, String avatarUrl, Date dateOfBirth, UserRole role, boolean isDeleted, float rate, Set<Booking> bookings, Set<PhotographerConcept> photographerConcepts
//            users.add(new Photographer(1,"1221","Trang","@gmail","123","", LocalDate.now(), UserRole.PHOTOGRAPHER,false,3.0f,null,null));
//            users.add(new Photographer(2,"1221","Banana","@gmail","123","", LocalDate.now(), UserRole.PHOTOGRAPHER,false,3.0f,null,null));
            adapter = new MyPhotographerListRecyclerViewAdapter(users);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}