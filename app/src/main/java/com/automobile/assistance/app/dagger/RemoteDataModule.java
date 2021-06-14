package com.automobile.assistance.app.dagger;

import com.automobile.assistance.data.remote.RemoteDataApi;
import com.automobile.assistance.data.remote.RetrofitService;
import com.automobile.assistance.data.remote.interceptor.TokenInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteDataModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService(TokenInterceptor interceptor) {
        return new RetrofitService(interceptor);
    }

    @Provides
    @Singleton
    RemoteDataApi provideRemoteDataApi(RetrofitService retrofitService) {
        return new RemoteDataApi(retrofitService);
    }

}
