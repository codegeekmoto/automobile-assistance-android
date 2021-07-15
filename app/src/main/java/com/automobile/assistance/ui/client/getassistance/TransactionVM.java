package com.automobile.assistance.ui.client.getassistance;

import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.Jobs;
import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class TransactionVM extends BaseVM {

    private MutableLiveData<Result> transactionResult = new MutableLiveData<>();
    private MutableLiveData<Result> completeJobResult = new MutableLiveData<>();

    public TransactionVM(UseCase useCase) {
        super(useCase);
    }

    public MutableLiveData<Result> getTransactionResult() {
        return transactionResult;
    }

    public MutableLiveData<Result> getCompleteJobResult() {
        return completeJobResult;
    }

    public void getTransaction(String type) {
        useCase.service().getTransactions("", type).execute(new DisposableObserver<Jobs>() {
            @Override
            public void onNext(@NonNull Jobs jobs) {
                transactionResult.setValue(new Result.Success<>(jobs));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                transactionResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void completeJob(String jobId) {
        useCase.service().completeJob(jobId).execute(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                completeJobResult.setValue(new Result.Success<>(aBoolean));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                printError(e);
                completeJobResult.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return TransactionVM.class;
    }
}
