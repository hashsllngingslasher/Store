package com.example.kindastoreapi.ui.payment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.kindastoreapi.R;
import com.example.kindastoreapi.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {
    FragmentPaymentBinding binding;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.
                inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.visa.setOnClickListener(v -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://visa.com/"));
            startActivity(myIntent);
        });

        binding.mastercard.setOnClickListener(v1 -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.mastercard.ru/ru-ru.html/"));
            startActivity(myIntent);
        });

        binding.mbank.setOnClickListener(v2 -> {
            String packageName = "com.maanavan.mb_kyrgyzstan";
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
//                Intent intent1 = new Intent(Intent.ACTION_VIEW);
//                intent1.setData(Uri.parse("market://details?id=" + packageName));
//                startActivity(intent1);
                navController = Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.myQrFragment);
            }
        });

        binding.oDengi.setOnClickListener(v3 -> {
            String packageName = "kg.o.nurtelecom";
            PackageManager packageManager = requireContext().getPackageManager();
            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);

            Log.e("pay", launchIntent.toString());
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            }
        });

        binding.btnBack.setOnClickListener(v4 -> {
            navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_payment_to_navigation_home);
        });
    }
}
