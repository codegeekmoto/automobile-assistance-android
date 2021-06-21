package com.automobile.assistance.ui.client.mymechanic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.FragmentMyMechanicBinding;
import com.automobile.assistance.ui.client.getassistance.AssistanceFragment;
import com.automobile.assistance.ui.client.getassistance.AssistanceVM;
import com.automobile.assistance.ui.client.getassistance.TireVM;
import com.automobile.assistance.ui.vmfactory.TireVMFactory;

import javax.inject.Inject;

public class MyMechanicsFragment extends AssistanceFragment {

    private FragmentMyMechanicBinding binding;

    @Inject
    TireVMFactory vmFactory;
    private TireVM tireVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        tireVM = new ViewModelProvider(this, vmFactory).get(TireVM.class);
        binding = FragmentMyMechanicBinding.inflate(inflater, container, false);

        initMap(binding.map);
        return binding.getRoot();
    }

    @Override
    protected RecyclerView serviceList() {
        return null;
    }

    @Override
    protected AssistanceVM getVm() {
        return tireVM;
    }

    @Override
    protected String getServiceId() {
        return "0";
    }

    @Override
    protected void onMapReady() {
        binding.progressLayout.parent.setVisibility(View.GONE);
    }
}
