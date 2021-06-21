package com.automobile.assistance.ui.client.getassistance;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.TireFragmentBinding;
import com.automobile.assistance.ui.vmfactory.TireVMFactory;

import javax.inject.Inject;

public class TireFragment extends AssistanceFragment {

    @Inject
    TireVMFactory vmFactory;
    private TireVM tireVM;

    private TireFragmentBinding binding;

    public static TireFragment newInstance() {
        return new TireFragment();
    }

    @Override
    protected RecyclerView serviceList() {
        return null; // binding.services.serviceList;
    }

    @Override
    protected AssistanceVM getVm() {
        return tireVM;
    }

    @Override
    protected String getServiceId() {
        return "2";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        tireVM = new ViewModelProvider(this, vmFactory).get(TireVM.class);
        binding = TireFragmentBinding.inflate(inflater, container, false);


        initMap(binding.map);
        return binding.getRoot();
    }

    @Override
    protected void onMapReady() {
        binding.progressLayout.parent.setVisibility(View.GONE);
    }
}