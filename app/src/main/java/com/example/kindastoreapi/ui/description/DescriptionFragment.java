package com.example.kindastoreapi.ui.description;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.kindastoreapi.R;
import com.example.kindastoreapi.databinding.FragmentDescriptionBinding;
import com.example.kindastoreapi.models.ModelM;

import java.util.ArrayList;

public class DescriptionFragment extends Fragment {
    private FragmentDescriptionBinding binding;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            ArrayList<ModelM> dList = getArguments().getParcelableArrayList("see more");
            Log.e("TAG", "check1");

            if (dList != null && !dList.isEmpty()) {
                Log.e("TAG", "check2");
                ModelM model = dList.get(0);
                binding.nameProductCard.setText(model.getModelTitle());
                Glide.with(requireContext())
                        .load(model.getModelImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_dashboard_black_24dp)
                        .into(binding.imageCard);
                binding.priceCard.setText(String.valueOf(model.getModelPrice()));
                binding.descriptionCard.setText(model.getModelDescription());
            }
        } else {
            Toast.makeText(requireActivity(), "There is nothing", Toast.LENGTH_SHORT).show();
        }

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_description_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}