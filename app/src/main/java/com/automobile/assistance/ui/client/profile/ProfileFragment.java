package com.automobile.assistance.ui.client.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.automobile.assistance.databinding.FragmentProfileBinding;
import com.automobile.assistance.ui.auth.AuthActivity;
import com.automobile.assistance.ui.vmfactory.ProfileVMFactory;
import com.automobile.assistance.util.Helper;
import com.automobile.assistance.util.ResultObserver;

import java.util.Objects;

import javax.inject.Inject;

public class ProfileFragment extends Fragment {

    @Inject
    ProfileVMFactory vmFactory;
    private ProfileVM profileVM;

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        profileVM = new ViewModelProvider(this, vmFactory).get(ProfileVM.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        setProfile();
        setVmObservers();
        setClickHandler();

        return binding.getRoot();
    }

    private void setProfile() {
        User user = profileVM.getUser();
        binding.name.setText(user.fullName());
        binding.email.setText(user.getEmail());
        binding.phone.setText(user.getPhone());
    }

    private void setVmObservers() {
        // Update email observer
        profileVM.getUpdateEmailResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    showEmailEditor(false);
                    setProfile();
                    Helper.dialogAlert(getContext(), Constant.Message.EMAIL_SUCCESS);
                } else {
                    binding.emailEditLayout.setError(Constant.Message.EMAIL_EXISTS);
                }
            }

            @Override
            public void onError(Throwable e) {
                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constant.Message.CANNOT_RESOLVE_HOST_ERROR;
                } else {
                    errMsg = Constant.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), errMsg);
            }
            @Override
            public void onComplete() {
                showLoading(false);
            }
        });

        // Update phone observer
        profileVM.getUpdatePhoneResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    showPhoneEditor(false);
                    setProfile();
                    Helper.dialogAlert(getContext(), Constant.Message.PHONE_SUCCESS);
                } else {
                    binding.phoneEditLayout.setError(Constant.Message.PHONE_EXISTS);
                }
            }

            @Override
            public void onError(Throwable e) {
                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constant.Message.CANNOT_RESOLVE_HOST_ERROR;
                } else {
                    errMsg = Constant.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), errMsg);
            }

            @Override
            public void onComplete() {
                showLoading(false);
            }
        });

        // Change password observer
        profileVM.getChangePasswordResult().observe(getViewLifecycleOwner(), new ResultObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    showChangePasswordEditor(false);
                    Helper.dialogAlert(getContext(), Constant.Message.CHANGE_PASSWORD_SUCCESS);
                } else {
                    binding.passwordEdit.setError(Constant.Message.CHANGE_PASSWORD_ERROR);
                }
            }

            @Override
            public void onError(Throwable e) {
                String errMsg = "";
                if (e.getMessage().contains("Unable to resolve host")) {
                    errMsg = Constant.Message.CANNOT_RESOLVE_HOST_ERROR;
                } else {
                    errMsg = Constant.Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), errMsg);
            }

            @Override
            public void onComplete() {
                showLoading(false);
            }
        });
    }

    private void setClickHandler() {
        // Change picture
        binding.btnChangePicture.setOnClickListener(v -> {
//            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(takePicture, AppConstant.CAMERA_INTENT_RESULT);
        });

        // Email editor
        Helper.addTextWatcher(binding.emailEdit, binding.emailEditLayout);
        binding.btnEmailEdit.setOnClickListener(v -> showEmailEditor(true));
        binding.btnEmailCancel.setOnClickListener(v -> showEmailEditor(false));
        binding.btnEmailSave.setOnClickListener(v -> {
            String email = Objects.requireNonNull(binding.emailEdit.getText()).toString();
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEditLayout.setError("Invalid email address.");
                return;
            }

            if (email.equals(profileVM.getUser().getEmail())) {
                binding.emailEditLayout.setError("This is your current email.");
                return;
            }

            showLoading(true);
            profileVM.updateEmail(email);
        });

        // Phone number
        Helper.addTextWatcher(binding.phoneEdit, binding.phoneEditLayout);
        binding.btnPhoneEdit.setOnClickListener(v -> showPhoneEditor(true));
        binding.btnPhoneCancel.setOnClickListener(v -> showPhoneEditor(false));
        binding.btnPhoneSave.setOnClickListener(v -> {
            String phone = Objects.requireNonNull(binding.phoneEdit.getText()).toString();
            if (!PhoneNumberUtils.isGlobalPhoneNumber(phone) || phone.trim().equals("")) {
                binding.phoneEditLayout.setError("Invalid phone number.");
                return;
            }

            if (phone.equals(profileVM.getUser().getPhone())) {
                binding.phoneEditLayout.setError("This is your current phone number.");
                return;
            }

            showLoading(true);
            profileVM.updatePhone(phone);
        });

        // Change address
        binding.addressLayout.setOnClickListener(v -> {

        });

        // Change password editor
        Helper.addTextWatcher(binding.passwordEdit, binding.passwordEditLayout);
        Helper.addTextWatcher(binding.confirmPasswordEdit, binding.confirmPasswordEditLayout);
        binding.btnChangePassword.setOnClickListener(v -> showChangePasswordEditor(true));
        binding.btnPasswordCancel.setOnClickListener(v -> showChangePasswordEditor(false));
        binding.btnPasswordSave.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.passwordEdit.getText()) || binding.passwordEdit.length() < 6) {
                binding.passwordEditLayout.setError("Password must be at least 6 characters.");
                return;
            }

            if (TextUtils.isEmpty(binding.confirmPasswordEdit.getText()) || !binding.passwordEdit.getText().toString().equals(binding.confirmPasswordEdit.getText().toString())) {
                binding.confirmPasswordEditLayout.setError("Please confirm password.");
                return;
            }

            showLoading(true);
            profileVM.changePassword(binding.passwordEdit.getText().toString());
        });

        // Logout
        binding.btnLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setMessage("Are you sure you want to logout your account?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            profileVM.logout();
                            startActivity(new Intent(getContext(), AuthActivity.class));
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .create()
                    .show();
        });
    }

    private void showEmailEditor(boolean show) {
        binding.emailText.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.emailForm.setVisibility(show ? View.VISIBLE : View.GONE);

        if (show) {
            binding.emailEdit.setText(profileVM.getUser().getEmail());
            showPhoneEditor(false);
            showChangePasswordEditor(false);
        } else {
            binding.emailEditLayout.setError("");
        }
    }

    private void showPhoneEditor(boolean show) {
        binding.phoneText.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.phoneForm.setVisibility(show ? View.VISIBLE : View.GONE);

        if (show) {
            binding.phoneEdit.setText(profileVM.getUser().getPhone());
            showEmailEditor(false);
            showChangePasswordEditor(false);
        } else {
            binding.phoneEditLayout.setError("");
        }
    }

    private void showChangePasswordEditor(boolean show) {
        binding.btnChangePassword.setVisibility(show ? View.GONE : View.VISIBLE);
        binding.passwordLayout.setVisibility(show ? View.VISIBLE : View.GONE);

        if (show) {
            binding.passwordEdit.setText("");
            binding.confirmPasswordEdit.setText("");
            showEmailEditor(false);
            showPhoneEditor(false);
        } else {
            binding.passwordEditLayout.setError("");
            binding.confirmPasswordEditLayout.setError("");
        }
    }

    private void showLoading(boolean enable) {
        Helper.enableView(binding.root, !enable);
        binding.progress.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Logger.V("REQUEST CODE", requestCode);
//        Logger.V("RESULT CODE", resultCode);
//        Logger.V("DATA CODE", data.getData());

//        if (requestCode == AppConstant.CAMERA_INTENT_RESULT) {
//
//        }
    }
}
