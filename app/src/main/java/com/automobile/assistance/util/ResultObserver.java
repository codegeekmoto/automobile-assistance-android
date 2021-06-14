package com.automobile.assistance.util;

public abstract class ResultObserver<T> implements androidx.lifecycle.Observer<Result> {

    @Override
    public void onChanged(Result result) {
        if (result instanceof Result.Success) {
            onSuccess(((Result.Success<T>) result).getData());
        } else {
            onError(((Result.Error) result).getError());
        }

        onComplete();
    }

    public void onSuccess(T t) {};
    public void onError(Throwable e) {};
    public void onComplete() {};
}
