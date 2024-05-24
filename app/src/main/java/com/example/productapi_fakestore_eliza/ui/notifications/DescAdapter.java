package com.example.productapi_fakestore_eliza.ui.notifications;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.productapi_fakestore_eliza.databinding.ItemDescBinding;
import com.example.productapi_fakestore_eliza.models.ModelM;

import java.util.ArrayList;
import java.util.List;


public class DescAdapter extends RecyclerView.Adapter<DescAdapter.ViewHolder> {

    ItemDescBinding binding;

    List<ModelM> listD = new ArrayList<>();

    public DescAdapter(FragmentActivity fragmentActivity, ArrayList<ModelM> dList) {
    }

    public void setListD(List<ModelM> listD) {
        this.listD = listD;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDescBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listD.get(position));
    }

    @Override
    public int getItemCount() {
        return listD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemDescBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.titleCard.setText(modelM.getModelTitle());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.descCard.setText(modelM.getModelDescription());
            Glide.with(itemView.getContext()).load(modelM.getModelImage()).into(binding.imageCard);
        }
    }
}