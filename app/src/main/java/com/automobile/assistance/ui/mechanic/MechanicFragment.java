package com.automobile.assistance.ui.mechanic;

import    android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.databinding.FragmentMechanicBinding;

public class MechanicFragment extends Fragment {

    private FragmentMechanicBinding binding;

    public MechanicFragment() {
        // Required empty public constructor
    }

    public static MechanicFragment newInstance() {
        return new MechanicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMechanicBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}