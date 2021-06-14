package com.automobile.assistance.data.remote.api;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Response;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST(Constant.API.USER_CREATE)
    Observable<Response> register(@Body RequestBody body);

    @POST(Constant.API.USER_LOGIN)
    Observable<Response>  login(@Body RequestBody body);

    @POST(Constant.API.USER_RESET_PASSWORD)
    Observable<Response>  reset(@Body RequestBody body);

    @POST(Constant.API.USER_RESEND_OTP)
    Observable<Response>  resendOTP(@Body RequestBody body);

    @POST(Constant.API.USER_CHANGE_PASSWORD)
    Observable<Response>  changePassword(@Body RequestBody body);


    @POST(Constant.API.USER_UPDATE_EMAIL)
    Observable<Response> updateEmail(@Body RequestBody body);

    @POST(Constant.API.USER_UPDATE_PHONE)
    Observable<Response> updatePhone(@Body RequestBody body);
}
