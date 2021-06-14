package com.automobile.assistance.data;

import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;
import com.automobile.assistance.data.remote.pojo.Response;
import com.automobile.assistance.data.remote.pojo.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

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

}
