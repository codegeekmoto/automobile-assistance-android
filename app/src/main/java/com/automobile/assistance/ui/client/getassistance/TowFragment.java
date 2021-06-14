package com.automobile.assistance.ui.client.getassistance;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.TowFragmentBinding;
import com.automobile.assistance.ui.vmfactory.TowVMFactory;

import javax.inject.Inject;

public class TowFragment extends AssistanceFragment {

    @Inject
    TowVMFactory vmFactory;
    private TowVM towVM;

    private TowFragmentBinding binding;

    public static TowFragment newInstance() {
        return new TowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        towVM = new ViewModelProvider(this, vmFactory).get(TowVM.class);
        binding = TowFragmentBinding.inflate(inflater, container, false);

        initMap(binding.map);
        return binding.getRoot();
    }

    @Override
    protected RecyclerView serviceList() {
        return null;// binding.services.serviceList;
    }

    @Override
    protected AssistanceVM getVm() {
        return towVM;
    }

    @Override
    protected String getServiceId() {
        return "1";
    }

    @Override
    protected void onMapReady() {
        binding.progressLayout.parent.setVisibility(View.GONE);
    }
}