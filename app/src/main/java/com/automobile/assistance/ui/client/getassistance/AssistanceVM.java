package com.automobile.assistance.ui.client.getassistance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public abstract class AssistanceVM extends BaseVM {

    protected MutableLiveData<Result> serviceResult = new MutableLiveData<>();

    protected AssistanceVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getServiceResult() {
        return serviceResult;
    }

    public void fetchService(String serviceId) {
        useCase.service().getService(serviceId).execute(new DisposableObserver<List<Service>>() {
            @Override
            public void onNext(@NonNull List<Service> services) {
                serviceResult.setValue(new Result.Success<>(services));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                serviceResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        });
    }

    protected void getMyCar() {

    }

    // Must be override in child vm
    @Override
    protected Class<?> resolveLoggerName() { return null; }
}
