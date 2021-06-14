package com.automobile.assistance.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.automobile.assistance.R;
import com.automobile.assistance.app.App;
import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.databinding.FragmentAuthLoginBinding;
import com.automobile.assistance.ui.client.ClientActivity;
import com.automobile.assistance.ui.vmfactory.LoginVMFactory;
import com.automobile.assistance.util.Helper;
import com.automobile.assistance.util.ResultObserver;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    LoginVMFactory vmFactory;
    private LoginVM loginVM;

    private FragmentAuthLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(LoginFragment.this);
        loginVM = new ViewModelProvider(this, vmFactory).get(LoginVM.class);

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Login");
        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        setLoginForm();
        setVmObserver();

         binding.email.setText("ken@mail.com");
         binding.password.setText("123123");

        return binding.getRoot();
    }

    private void setVmObserver() {
        loginVM.getLoginResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(getActivity(), ClientActivity.class));
                    getActivity().finish();
                } else {
                    Helper.dialogAlert(getContext(), "Login Failed", "Incorrect email or password. Try again.");
                }
            }

            @Override
            public void onError(Throwable e) {

                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constant.Message.LOGIN_INTERNET_ERROR;
                } else if (e.getMessage().contains("401 Unauthorized")) {
                    errMsg = "Incorrect email or password. Try again.";
                } else {
                    errMsg = e.getMessage(); //Constant.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Login Failed", errMsg);
            }

            @Override
            public void onComplete() {
                enabledLoginForm(true);
            }
        });
    }

    private void setLoginForm() {
        Helper.addTextWatcher(binding.email, binding.emailLayout);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                        .navigate(R.id.auth_register);
            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
//                        .navigate(R.id.auth_forgot_password);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
                    binding.emailLayout.setError("Invalid email address.");
                } else if (TextUtils.isEmpty(binding.password.getText())) {
                    binding.passwordLayout.setError("Password is required!");
                } else {
                    enabledLoginForm(false);
                    User user = new User();
                    user.setEmail(binding.email.getText().toString());
                    user.setPassword(binding.password.getText().toString());
                    user.setRole("customer");
                    loginVM.login(user);
                }
            }
        });
    }

    private void enabledLoginForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnLogin.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }
}
