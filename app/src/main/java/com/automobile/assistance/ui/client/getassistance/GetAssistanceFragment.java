package com.automobile.assistance.ui.client.getassistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.automobile.assistance.R;
import com.automobile.assistance.databinding.FragmentGetAssistanceBinding;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;

public class GetAssistanceFragment extends Fragment {

    private Logger LOG = LoggerFactory.getLogger(GetAssistanceFragment.class);

    private FragmentGetAssistanceBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGetAssistanceBinding.inflate(inflater, container, false);

        NavController navigation = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        binding.btnLockout.setOnClickListener(v -> {
           navigation.navigate(R.id.action_nav_get_assistance_to_inner_assistance_lockout);
        });

        binding.btnTow.setOnClickListener(v -> {
            navigation.navigate(R.id.action_nav_get_assistance_to_inner_assistance_tow);
        });

        binding.btnTire.setOnClickListener(v -> {
            navigation.navigate(R.id.action_nav_get_assistance_to_inner_assistance_tire);
        });

        binding.btnFuel.setOnClickListener(v -> {
            navigation.navigate(R.id.action_nav_get_assistance_to_inner_assistance_fuel);
        });

        binding.btnMechanic.setOnClickListener(v -> {
            navigation.navigate(R.id.action_nav_get_assistance_to_nav_my_mechanic);
        });

        return binding.getRoot();
    }

    private void setSpinners() {
        // Services
//        String[] services = {"Jump", "Lockout", "Tow", "Tire", "Fuel"};
//        Spinner spinner = binding.spinnerServices;
//
//        spinner.setPrompt("Select service");
//        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, services);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        // Cars
//        String[] cars = {"Toyota HIACE", "Suzuki Gixer", "BMW", "Ferrari", "Honda Civic"};
//        Spinner spinnerCar = binding.spinnerCars;
//
//
//        spinnerCar.setPrompt("Select your car");
//        ArrayAdapter spinnerCarAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, cars);
//        spinnerCarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCar.setAdapter(spinnerCarAdapter);
    }

}