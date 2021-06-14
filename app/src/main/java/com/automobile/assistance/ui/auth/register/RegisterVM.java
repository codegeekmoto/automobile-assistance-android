package com.automobile.assistance.ui.auth.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class RegisterVM extends BaseVM {

    private MutableLiveData<Result> registerResult = new MutableLiveData<>();

    public RegisterVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getRegisterResult() {
        return registerResult;
    }

    public void register(User user) {
        useCase.user().register(user).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                registerResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LOG.error(e.getMessage());
                e.printStackTrace();

                registerResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return RegisterVM.class;
    }
}
