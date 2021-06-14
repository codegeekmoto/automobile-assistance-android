package com.automobile.assistance.data.remote.api;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Response;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CarApi {

    @POST(Constant.API.CAR_CREATE)
    Observable<Response> create(@Body RequestBody body);

    @POST(Constant.API.CAR_DELETE+"/{id}")
    Observable<Response> delete(@Path(value = "id", encoded = true) String id);

    @GET(Constant.API.CAR_ALL)
    Observable<Response> getAll();
}
