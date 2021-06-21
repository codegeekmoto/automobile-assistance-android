package com.automobile.assistance.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.automobile.assistance.R;
import com.automobile.assistance.databinding.FragmentSelectLoginBinding;

import org.jetbrains.annotations.NotNull;

public class SelectLoginFragment extends Fragment {

    private FragmentSelectLoginBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentSelectLoginBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Choose Login");
        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.btnCreateAccount.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_register);
        });

        binding.btnCustomer.setOnClickListener(v -> {
            navigateToLogin("customer");
        });

        binding.btnMechanic.setOnClickListener(v -> {
            navigateToLogin("mechanic");
        });

        return binding.getRoot();
    }

    private void navigateToLogin(String loginType) {
        Bundle bundle = new Bundle();
        bundle.putString("login_type", loginType);
        Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                .navigate(R.id.auth_login, bundle);
    }

}
