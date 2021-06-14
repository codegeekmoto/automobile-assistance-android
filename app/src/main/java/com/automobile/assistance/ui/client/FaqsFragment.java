package com.automobile.assistance.ui.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.automobile.assistance.databinding.FragmentFaqBinding;

public class FaqsFragment extends Fragment {

    private FragmentFaqBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
