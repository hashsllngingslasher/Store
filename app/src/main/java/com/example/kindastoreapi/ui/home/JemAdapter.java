package com.example.kindastoreapi.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kindastoreapi.R;
import com.example.kindastoreapi.databinding.ItemProductBinding;
import com.example.kindastoreapi.models.ModelM;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class JemAdapter extends RecyclerView.Adapter<JemAdapter.ViewHolder> {
    ItemProductBinding binding;
    Context context;
    List<ModelM> list;
    ArrayList<ModelM> selected_list = new ArrayList<>();
    ArrayList<ModelM> selected_intoBasketList = new ArrayList<>();
    NavController navController;

    public JemAdapter(Context context, List<ModelM> list) {
        this.context = context;
        this.list = list;
    }

    public ArrayList<ModelM> getSelected_intoBasketList() {
        return selected_intoBasketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProductBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Double getItemPrice(int position) {
        return list.get(position).getModelPrice();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;

        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void onBind(ModelM modelM) {
            binding.nameProductCard.setText(modelM.getModelTitle());
            binding.priceCard.setText(String.valueOf(modelM.getModelPrice()));
            binding.abilityCard.setText(modelM.getModelCategory());

            Glide.with(context)
                    .load(list.get(getAdapterPosition()).getModelImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_dashboard_black_24dp)
                    .into(binding.imageCard);

            binding.btnZoom.setOnClickListener(v -> {
                selected_list.add(modelM);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("see more", selected_list);

                navController = Navigation.findNavController((Activity) itemView.getContext(),
                        R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.action_navigation_home_to_navigation_description, bundle);
                Log.e("TAG", "pass data to description!");
            });
            itemView.setOnClickListener(v1 -> {
                if (binding.tovarFavoriteCheck.getVisibility() == View.INVISIBLE) {
                    binding.tovarFavoriteCheck.setVisibility(View.VISIBLE);

                    selected_intoBasketList.add(modelM);
                } else {
                    binding.tovarFavoriteCheck.setVisibility(View.INVISIBLE);
                    selected_intoBasketList.remove(modelM);
                }
            });
        }
    }
}
