package com.automobile.assistance.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;
import com.automobile.assistance.data.remote.pojo.FcmToken;
import com.automobile.assistance.data.remote.pojo.Response;
import com.automobile.assistance.data.remote.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserSource extends DataSource {

    @Inject
    public UserSource(Prefs prefsApi, RemoteDataApi remoteApi, Database dbApi) {
        super(prefsApi, remoteApi, dbApi);
    }

    public Observable<Boolean> register(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fname", user.getFname())
                .addFormDataPart("lname", user.getLname())
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", user.getPassword())
                .addFormDataPart("phone", user.getPhone())
                .addFormDataPart("role", user.getRole())
                .addFormDataPart("picture", "")
                .build();

        return remoteApi.auth().register(body).map(new Function<Response, Boolean>() {
            @Override
            public Boolean apply(Response response) throws Throwable {
                return response.getStatus();
            }
        });
    }

    public Observable<User> login(User user) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", user.getEmail())
                .addFormDataPart("password", user.getPassword())
                .addFormDataPart("role", user.getRole())
                .build();

        return remoteApi.auth().login(body).map(new Function<Response, User>() {
            @Override
            public User apply(Response response) throws Throwable {
                if (response.getStatus()) {
                    prefsApi.setObject(Constant.Prefs.USER, response.getUser());
                    return response.getUser();
                } else {
                    return new User();
                }
            }
        });
    }

    public Observable<Boolean> initFCM(String token, String id) {
        User user = getUser();
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("user_id", String.valueOf(user.getId()))
                .addFormDataPart("token", token)
                .build();

        return remoteApi.user().saveFcmToken(body).map(new Function<Response, Boolean>() {
            @Override
            public Boolean apply(Response response) throws Throwable {
                return true;
            }
        });
    }

    public Observable<Boolean> updateEmail(String email) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .build();

        return remoteApi.user().updateEmail(body).map(response -> {
            if (response.getStatus()) {
                prefsApi.setObject(Constant.Prefs.USER, response.getUser());
                return true;
            }

            return false;
        });
    }

    public Observable<Boolean> updatePhone(String phone) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("phone", phone)
                .build();

        return remoteApi.user().updatePhone(body).map(response -> {
            if (response.getStatus()) {
                prefsApi.setObject(Constant.Prefs.USER, response.getUser());
                return true;
            }

            return false;
        });
    }

    public Observable<Boolean> changePassword(String password) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", String.valueOf(getUser().getId()))
                .addFormDataPart("password", password)
                .build();

        return remoteApi.user().changePassword(body).map(Response::getStatus);
    }

    public User getUser() {
        return prefsApi.getObject(Constant.Prefs.USER, User.class);
    }

    public void logout() {
        prefsApi.clear();
    }

    public boolean isLoggedIn() {
       return getUser() != null;
    }


}
