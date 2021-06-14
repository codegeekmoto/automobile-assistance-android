package com.automobile.assistance.ui.client.getassistance;


import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LockoutVM extends AssistanceVM {

    public LockoutVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return LockoutVM.class;
    }

    public void test() {
        useCase.lockout().insert().execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}