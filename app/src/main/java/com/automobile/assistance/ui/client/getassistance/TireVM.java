package com.automobile.assistance.ui.client.getassistance;


import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.util.Result;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class TireVM extends AssistanceVM {

    private MutableLiveData<Result> mechanicsResult = new MutableLiveData<>();

    public TireVM(UseCase useCase) {
        super(useCase);
    }

    public MutableLiveData<Result> getMechanicsResult() {
        return mechanicsResult;
    }

    public void getMechanics() {
        useCase.service().getMechanics().execute(new DisposableObserver<List<User>>() {
            @Override
            public void onNext(@NonNull List<User> users) {
                mechanicsResult.setValue(new Result.Success<>(users));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                mechanicsResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return TireVM.class;
    }
}