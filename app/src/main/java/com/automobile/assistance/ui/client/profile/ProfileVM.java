package com.automobile.assistance.ui.client.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.app.App;
import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class ProfileVM extends BaseVM {

    private MutableLiveData<Result> updateEmailResult = new MutableLiveData<>();
    private MutableLiveData<Result> updatePhoneResult = new MutableLiveData<>();
    private MutableLiveData<Result> changePasswordResult = new MutableLiveData<>();

    public ProfileVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getUpdateEmailResult() {
        return updateEmailResult;
    }

    public LiveData<Result> getUpdatePhoneResult() {
        return updatePhoneResult;
    }

    public LiveData<Result> getChangePasswordResult() {
        return changePasswordResult;
    }

    public User getUser() {
        return useCase.user().getUser();
    }

    public void logout() {
        App.getInstance().repository().service().setAssistanceAcceptedFlag(false);
        useCase.user().logout();
    }

    public void updateEmail(String email) {
        useCase.user().updateEmail(email).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                updateEmailResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                updateEmailResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updatePhone(String phone) {
        useCase.user().updatePhone(phone).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                updatePhoneResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
                updatePhoneResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void changePassword(String password) {
        useCase.user().updatePhone(password).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                changePasswordResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
                changePasswordResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return ProfileVM.class;
    }
}
