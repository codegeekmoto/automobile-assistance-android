package com.automobile.assistance.ui.client.getassistance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.FragmentBaseBinding;
import com.automobile.assistance.otto.AssistanceEvent;
import com.squareup.otto.Subscribe;

public class BaseFragment extends Fragment {

    private FragmentBaseBinding binding;

    public BaseFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance() {
        return new BaseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBaseBinding.inflate(inflater, container, false);

        Fragment fragment;

//        if (App.getInstance().repository().service().getAssistanceAcceptedFlag()) {
//            fragment = new TransacFragment();
//        } else {
//            fragment = new GetAssistanceFragment();
//        }

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.base_fragment, new GetAssistanceFragment());
        ft.commit();

        return binding.getRoot();
    }
}