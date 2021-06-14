package com.automobile.assistance.ui.auth.register;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.automobile.assistance.app.App;
import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.databinding.FragmentAuthRegisterBinding;
import com.automobile.assistance.ui.vmfactory.RegisterVMFactory;
import com.automobile.assistance.util.Helper;
import com.automobile.assistance.util.ResultObserver;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {

    private FragmentAuthRegisterBinding binding;

    @Inject
    RegisterVMFactory vmFactory;
    private RegisterVM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(RegisterFragment.this);
        vm = new ViewModelProvider(this, vmFactory).get(RegisterVM.class);

        binding = FragmentAuthRegisterBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Register");
        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        initForm();
        setVmObserver();

        return binding.getRoot();
    }

    private void setVmObserver() {
        vm.getRegisterResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                clearForm();
                Helper.dialogAlert(getContext(), "Registration Success", Constant.Message.REGISTER_SUCCESS);
            }

            @Override
            public void onError(Throwable e) {

                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constant.Message.REGISTER_INTERNET_ERROR;
                } else {
                    errMsg = Constant.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Registration Failed", errMsg);
            }

            @Override
            public void onComplete() {
                enabledRegistrationForm(true);
            }
        });
    }

    private void initForm() {
        Helper.addTextWatcher(binding.firstName, binding.firstNameLayout);
        Helper.addTextWatcher(binding.lastName, binding.lastNameLayout);
        Helper.addTextWatcher(binding.phone, binding.phoneLayout);
        Helper.addTextWatcher(binding.email, binding.emailLayout);
        Helper.addTextWatcher(binding.password, binding.passwordLayout);
        Helper.addTextWatcher(binding.confirmPassword, binding.confirmPasswordLayout);

        binding.btnRegister.setOnClickListener(v -> {
            if (validateForm()) {
                User user = new User();
                user.setFname(binding.firstName.getText().toString());
                user.setLname(binding.firstName.getText().toString());
                user.setPhone(binding.phone.getText().toString());
                user.setEmail(binding.email.getText().toString());
                user.setPassword(binding.password.getText().toString());
                user.setRole("customer");
                enabledRegistrationForm(false);
                vm.register(user);
            }
        });

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());
    }

    private void enabledRegistrationForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnRegister.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void clearForm() {
        binding.firstName.getText().clear();
        binding.lastName.getText().clear();
        binding.phone.getText().clear();
        binding.email.getText().clear();
        binding.password.getText().clear();
        binding.confirmPassword.getText().clear();
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.firstName.getText())) {
            binding.firstNameLayout.setError("Invalid first name");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.lastName.getText())) {
            binding.lastNameLayout.setError("Invalid last name.");
            noError = false;
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(binding.phone.getText().toString()) || binding.phone.getText().toString().trim().equals("")) {
            binding.phoneLayout.setError("Invalid phone number.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
            binding.emailLayout.setError("Invalid email address.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.password.getText()) || binding.password.length() < 6) {
            binding.passwordLayout.setError("Password must be at least 6 characters.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.confirmPassword.getText()) || !binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            binding.confirmPasswordLayout.setError("Please confirm password.");
            noError = false;
        }

        return noError;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        vm.onCleared();
//    }
}
