package com.example.phobo.View.Adapter;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phobo.model.Photographer;
import com.example.phobo.R;
import com.example.phobo.databinding.FragmentItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Photographer}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPhotographerListRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotographerListRecyclerViewAdapter.ViewHolder> {

    private final List<Photographer> mValues;

    public MyPhotographerListRecyclerViewAdapter(List<Photographer> items) {
        mValues = items;

        System.out.println("SIZE"+mValues.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("DEBUG", "onBindViewHolder: "+position+" ;"+mValues.get(position).toString());
        holder.mItem = mValues.get(position);
        holder.mRate.setText("Rate: "+mValues.get(position).getRate());
        holder.mName.setText("Name: "+mValues.get(position).getName());
        holder.mRole.setText(mValues.get(position).getRole().toString());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("userDetail", mValues.get(position));
                Navigation.findNavController(v).navigate(R.id.detailFragment, bundle);
            }
        });
        Picasso.get().load(mValues.get(position).getAvatarUrl()).into(holder.mPhoto, new Callback() {
            @Override
            public void onSuccess() {
//                    normalViewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRate;
        public final TextView mRole;
        public final TextView mName;
        public final ImageView mPhoto;
        public final Button mButton;

        public Photographer mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mName = binding.txtName;
            mRole = binding.txtRole;
            mRate = binding.txtRate;
            mPhoto = binding.ivPhotographer;
            mButton = binding.btnDetail;

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }


}