package com.automobile.assistance.data;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;
import com.automobile.assistance.data.remote.pojo.Alert;
import com.automobile.assistance.data.remote.pojo.Response;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.User;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ServiceSource extends DataSource {

    @Inject
    public ServiceSource(Prefs prefsApi, RemoteDataApi remoteApi, Database dbApi) {
        super(prefsApi, remoteApi, dbApi);
    }

    public Observable<List<Service>> getServices(String serviceId) {
        return remoteApi.service().getByServiceId(serviceId).map(new Function<Response, List<Service>>() {
            @Override
            public List<Service> apply(Response response) throws Throwable {
                return response.getStatus() ? response.getServices() : new ArrayList<>();
            }
        });
    }

    public Observable<Alert> getAssistance(Service service, LatLng latLng) {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("service_id", service.getCompanyServiceId().toString())
                .addFormDataPart("user_id", getUser().getId().toString())
                .addFormDataPart("lat", String.valueOf(latLng.getLatitude()))
                .addFormDataPart("lng", String.valueOf(latLng.getLongitude()))
                .build();

        return remoteApi.service().getAssistance(body).map(new Function<Response, Alert>() {
            @Override
            public Alert apply(Response response) throws Throwable {
                return response.getAlert();
            }
        });
    }

    public User getUser() {
        return prefsApi.getObject(Constant.Prefs.USER, User.class);
    }

}
