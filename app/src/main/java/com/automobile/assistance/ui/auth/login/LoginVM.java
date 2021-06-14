package com.automobile.assistance.ui.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LoginVM extends BaseVM {

    private MutableLiveData<Result> loginResult = new MutableLiveData<>();

    public LoginVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getLoginResult() {
        return loginResult;
    }

    public void login(User user) {
        useCase.user().login(user).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                loginResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOG.error(e.getMessage());
                loginResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return LoginVM.class;
    }
}
