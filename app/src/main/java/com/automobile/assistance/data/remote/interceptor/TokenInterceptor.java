package com.automobile.assistance.data.remote.interceptor;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.remote.pojo.User;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Inject
    Prefs prefs;

    @Inject
    public TokenInterceptor() {}

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", prefs.getString(Constant.Prefs.COOKIE));
        builder.addHeader("authorization", "Bearer "+ prefs.getObject(Constant.Prefs.USER, User.class).getToken());

        return chain.proceed(builder.build());
    }
}
