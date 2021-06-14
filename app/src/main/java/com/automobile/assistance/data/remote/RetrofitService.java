package com.automobile.assistance.data.remote;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.interceptor.TokenInterceptor;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private TokenInterceptor tokenInterceptor;

    public RetrofitService(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    public static OkHttpClient.Builder createClient() {
        OkHttpClient.Builder client =  new OkHttpClient.Builder();
        client.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return client;
    }

    public Retrofit getDefault() {
        OkHttpClient.Builder client = createClient();
        return new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public Retrofit getAuthenticated() {
        OkHttpClient.Builder client = createClient();
        client.interceptors().add(tokenInterceptor);

        return new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }
}
