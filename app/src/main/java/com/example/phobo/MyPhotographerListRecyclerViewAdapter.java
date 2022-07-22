package com.example.phobo;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phobo.Model.User;
import com.example.phobo.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.phobo.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPhotographerListRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotographerListRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    private static List<User> users;

    public MyPhotographerListRecyclerViewAdapter(List<PlaceholderItem> items, List<User> photographers) {
        mValues = items;
        users = photographers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mRate.setText("5.0");
        holder.mName.setText(users.get(position).getName());
        holder.mRole.setText(users.get(position).getRole().toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRate;
        public final TextView mRole;
        public final TextView mName;

        public PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mName = binding.txtName;
            mRole = binding.txtRole;
            mRate = binding.txtRate;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}