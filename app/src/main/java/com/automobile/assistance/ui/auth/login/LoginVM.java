package com.automobile.assistance.ui.auth.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.automobile.assistance.data.remote.pojo.User;
import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;
import com.automobile.assistance.util.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

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
        useCase.user().login(user).execute(new DisposableObserver<User>() {
            @Override
            public void onNext(@NonNull User User) {
                saveFcmTk(user);
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

    public void saveFcmTk(User user) {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e("FIREBASE LOG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        useCase.user().saveFcmToken(token, String.valueOf(user.getId())).execute(new DisposableObserver<Boolean>() {
                            @Override
                            public void onNext(@NonNull Boolean aBoolean) {
                                loginResult.setValue(new Result.Success<>(user));
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                useCase.user().logout();
                                LOG.error(e.getMessage());
                                loginResult.setValue(new Result.Error(e));
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                    }
                });
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return LoginVM.class;
    }
}
