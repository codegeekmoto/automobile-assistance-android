package com.automobile.assistance.ui.client.getassistance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.FuelFragmentBinding;
import com.automobile.assistance.otto.AssistanceEvent;
import com.automobile.assistance.ui.vmfactory.FuelVMFactory;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class FuelFragment extends AssistanceFragment {

    @Inject
    FuelVMFactory vmFactory;
    private FuelVM fuelVM;

    private FuelFragmentBinding binding;

    public static FuelFragment newInstance() {
        return new FuelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        App.getEventBus().register(this);
        fuelVM = new ViewModelProvider(this, vmFactory).get(FuelVM.class);

        binding = FuelFragmentBinding.inflate(inflater, container, false);
        initMap(binding.map);
        return binding.getRoot();
    }

    @Override
    protected RecyclerView serviceList() {
        return null;
    }

    @Override
    protected AssistanceVM getVm() {
        return fuelVM;
    }

    @Override
    protected String getServiceId() {
        return "2";
    }

    @Override
    protected void onMapReady() {
        binding.progressLayout.parent.setVisibility(View.GONE);
    }

    @Subscribe
    public void onAssistanceNotification(AssistanceEvent event) {
        onMessage(event);
    }

    @Override
    public void onDestroy() {
        App.getEventBus().unregister(this);
        super.onDestroy();
    }
}