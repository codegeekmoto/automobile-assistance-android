package com.automobile.assistance.data.remote.api;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Response;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {

    @GET(Constant.API.SERVICE+"/{serviceId}")
    Observable<Response> get(@Path(value = "serviceId", encoded = true) String id);

    @GET(Constant.API.SERVICE_BY_ID+"/{serviceId}")
    Observable<Response> getByServiceId(@Path(value = "serviceId", encoded = true) String id);

    @POST(Constant.API.GET_ASSISTANCE)
    Observable<Response> getAssistance(@Body RequestBody body);

    @POST(Constant.API.TRANSACTIONS)
    Observable<Response> getTransactions(@Body RequestBody body);

    @POST(Constant.API.COMPLETED)
    Observable<Response> completeJob(@Body RequestBody body);

    @GET("mechanics")
    Observable<Response> mechanics();
}
