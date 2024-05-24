package com.example.productapi_fakestore_eliza.ui.notifications;

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

import com.example.productapi_fakestore_eliza.R;
import com.example.productapi_fakestore_eliza.databinding.FragmentDescriptionBinding;
import com.example.productapi_fakestore_eliza.models.ModelM;
import com.example.productapi_fakestore_eliza.ui.home.HomeViewModel;

import java.util.ArrayList;

public class DescriptionFragment extends Fragment {

    private FragmentDescriptionBinding binding;
    NavController navController;
    DescAdapter adapter;
    ArrayList<ModelM> d_list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DescriptionViewModel descriptionViewModel =
                new ViewModelProvider(this).get(DescriptionViewModel.class);
        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if(getArguments()!=null){
            d_list = getArguments().getParcelableArrayList("see more");
            Log.e("TAG", "DATA GETTING!!!");
            adapter = new DescAdapter(requireActivity(), d_list);
            binding.rvDescription.setAdapter(adapter);
        } else {
            Toast.makeText(requireActivity(), "There are nothing", Toast.LENGTH_SHORT);
        }


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setOnClickListener(v-> {
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