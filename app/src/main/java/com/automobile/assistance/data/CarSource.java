package com.automobile.assistance.data;

import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CarSource extends DataSource {

    @Inject
    public CarSource(Prefs prefsApi, RemoteDataApi remoteApi, Database dbApi) {
        super(prefsApi, remoteApi, dbApi);
    }

//    public Observable<Car> insert(Car car) {
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("year", car.getYear())
//                .addFormDataPart("model", car.getModel())
//                .addFormDataPart("make", car.getMake())
//                .addFormDataPart("color", car.getColor())
//                .build();
//
//        return remoteApi.car().create(body).map(new Function<Response, Car>() {
//            @Override
//            public Car apply(Response response) throws Throwable {
//                return response.getData().getCar();
//            }
//        });
//    }
//
//    public Observable<List<Car>> myCar() {
//        return remoteApi.car().getAll().map(new Function<Response, List<Car>>() {
//            @Override
//            public List<Car> apply(Response response) throws Throwable {
//                return response.getData().getCars();
//            }
//        });
//    }

 }
