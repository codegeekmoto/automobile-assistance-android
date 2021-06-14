package com.automobile.assistance.interactor;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class Thread<T>  {

    private CompositeDisposable disposables;

    protected Thread() {
        disposables = new CompositeDisposable();
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public void execute(DisposableObserver<T> observer) {
        Observable<T> observable = buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(observer));
    }

    public void longPolling(Observable<T> observer) {

    }

    public void dispose() {
        if (! disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
