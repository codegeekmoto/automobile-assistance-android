package com.automobile.assistance.data.remote;

import com.automobile.assistance.data.remote.api.CarApi;
import com.automobile.assistance.data.remote.api.FuelApi;
import com.automobile.assistance.data.remote.api.LockoutApi;
import com.automobile.assistance.data.remote.api.ServiceApi;
import com.automobile.assistance.data.remote.api.TireApi;
import com.automobile.assistance.data.remote.api.TowApi;
import com.automobile.assistance.data.remote.api.UserApi;

public class RemoteDataApi {

    private RetrofitService retrofitService;

    public RemoteDataApi(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    public UserApi user() {
        return retrofitService.getAuthenticated().create(UserApi.class);
    }

    public UserApi auth() {
        return retrofitService.getDefault().create(UserApi.class);
    }

    public LockoutApi lockout() {
        return retrofitService.getAuthenticated().create(LockoutApi.class);
    }

    public FuelApi fuel() {
        return retrofitService.getAuthenticated().create(FuelApi.class);
    }

    public TowApi tow() {
        return retrofitService.getAuthenticated().create(TowApi.class);
    }

    public TireApi tire() {
        return retrofitService.getAuthenticated().create(TireApi.class);
    }

    public CarApi car() {
        return retrofitService.getAuthenticated().create(CarApi.class);
    }

    public ServiceApi service() {
        return retrofitService.getAuthenticated().create(ServiceApi.class);
    }
}
