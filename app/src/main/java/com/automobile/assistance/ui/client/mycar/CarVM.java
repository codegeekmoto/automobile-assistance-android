package com.automobile.assistance.ui.client.mycar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class CarVM extends BaseVM {

    private MutableLiveData<Result> carAddResult = new MutableLiveData<>();
    private MutableLiveData<Result> myCarsResult = new MutableLiveData<>();

    public CarVM(UseCase useCase) {
        super(useCase);
    }

    public LiveData<Result> getCarAddResult() {
        return carAddResult;
    }

    public LiveData<Result> getMyCarsResult() {
        return myCarsResult;
    }

//    public void add(Car car) {
//
//        useCase.car().insert(car).execute(new DisposableObserver<Car>() {
//            @Override
//            public void onNext(@NonNull Car car) {
//                carAddResult.setValue(new Result.Success<>(car));
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                printError(e);
//                carAddResult.setValue(new Result.Error(e));
//            }
//
//            @Override
//            public void onComplete() {}
//        });
//    }
//
//    public void myCars() {
//        useCase.car().myCars().execute(new DisposableObserver<List<Car>>() {
//            @Override
//            public void onNext(@NonNull List<Car> cars) {
//                myCarsResult.setValue(new Result.Success<>(cars));
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                printError(e);
//                myCarsResult.setValue(new Result.Error(e));
//            }
//
//            @Override
//            public void onComplete() {}
//        });
//    }

    public void delete() {

    }

    @Override
    protected Class<?> resolveLoggerName() {
        return CarVM.class;
    }
}