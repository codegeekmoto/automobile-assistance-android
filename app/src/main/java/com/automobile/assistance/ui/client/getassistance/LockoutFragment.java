package com.automobile.assistance.ui.client.getassistance;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.LockoutFragmentBinding;
import com.automobile.assistance.otto.AssistanceEvent;
import com.automobile.assistance.ui.vmfactory.LockoutVMFactory;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class LockoutFragment extends AssistanceFragment {

    private Logger LOG = LoggerFactory.getLogger(LockoutFragment.class);

    @Inject
    LockoutVMFactory vmFactory;
    private LockoutVM lockoutVM;

    private LockoutFragmentBinding binding;

    public static LockoutFragment newInstance() {
        return new LockoutFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        App.getEventBus().register(this);

        lockoutVM = new ViewModelProvider(this, vmFactory).get(LockoutVM.class);
        binding = LockoutFragmentBinding.inflate(inflater, container, false);

        initMap(binding.map);
        return binding.getRoot();
    }

    @Override
    protected RecyclerView serviceList() {
        return null;// binding.services.serviceList;
    }

    @Override
    protected AssistanceVM getVm() {
        return lockoutVM;
    }

    @Override
    protected String getServiceId() {
        return "4";
    }

    @Override
    protected void onMapReady() {
        binding.progressLayout.parent.setVisibility(View.GONE);
    }

    @Subscribe
    public void onAssistanceNotification(AssistanceEvent event) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onMessage(event);
            }
        });
    }

    @Override
    public void onDestroy() {
        App.getEventBus().unregister(this);
        super.onDestroy();
    }
}