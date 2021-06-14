package com.automobile.assistance.data.remote.api;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Response;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi {

    @GET(Constant.API.SERVICE+"/{serviceId}")
    Observable<Response> get(@Path(value = "serviceId", encoded = true) String id);

    @GET(Constant.API.SERVICE_BY_ID+"/{serviceId}")
    Observable<Response> getByServiceId(@Path(value = "serviceId", encoded = true) String id);
}
