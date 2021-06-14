package com.automobile.assistance.ui.client.mycar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.app.App;
import com.automobile.assistance.databinding.AddCarFragmentBinding;
import com.automobile.assistance.otto.EventBus;
import com.automobile.assistance.otto.event.NewCarAddedEvent;
import com.automobile.assistance.ui.vmfactory.CarVMFactory;
import com.automobile.assistance.util.Helper;
import com.automobile.assistance.util.ResultObserver;

import javax.inject.Inject;

public class AddCarFragment extends Fragment {

    @Inject
    CarVMFactory vmFactory;
    private CarVM carVM;

    private AddCarFragmentBinding binding;

    public static AddCarFragment newInstance() {
        return new AddCarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        carVM = new ViewModelProvider(this, vmFactory).get(CarVM.class);
        binding = AddCarFragmentBinding.inflate(inflater);

        setVMObserver();
        initForm();
        return binding.getRoot();
    }

    private void setVMObserver() {
//        carVM.getCarAddResult().observe(getViewLifecycleOwner(), new ResultObserver<Car>() {
//            @Override
//            public void onSuccess(Car car) {
//                clearForm();
//                Helper.snackBar(binding.getRoot(), "Car successfully added");
//                EventBus.getInstance().post(new NewCarAddedEvent(car));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Helper.showDialogError(getContext(), e, "Failed Adding Car");
//            }
//
//            @Override
//            public void onComplete() {
//                enabledForm(true);
//            }
//        });
    }

    private void initForm() {
        Helper.addTextWatcher(binding.year, binding.yearLayout);
        Helper.addTextWatcher(binding.make, binding.makeLayout);
        Helper.addTextWatcher(binding.model, binding.modelLayout);
        Helper.addTextWatcher(binding.color, binding.colorLayout);

        binding.btnSave.setOnClickListener(v -> {
            if (validateForm()) {
//                Car car = new Car();
//                car.setYear(binding.year.getText().toString());
//                car.setMake(binding.make.getText().toString());
//                car.setModel(binding.model.getText().toString());
//                car.setColor(binding.color.getText().toString());
//
//                enabledForm(false);
//                carVM.add(car);
            }
        });
    }

    private void enabledForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnSave.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void clearForm() {
        binding.year.getText().clear();
        binding.make.getText().clear();
        binding.model.getText().clear();
        binding.color.getText().clear();
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.year.getText())) {
            binding.year.setError("Year is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.make.getText())) {
            binding.make.setError("Make is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.model.getText())) {
            binding.model.setError("Model is required");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.color.getText())) {
            binding.color.setError("Color is required");
            noError = false;
        }

        return noError;
    }
}