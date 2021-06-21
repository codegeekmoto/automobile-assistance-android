package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.remote.pojo.Alert;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.Service22;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class ServiceUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    @Inject
    public ServiceUseCase() {
    }

    public GetService getService(String serviceId) {
        GetService getService = new GetService(serviceId);
        threads.add(getService);
        return getService;
    }

    public GetAssistance getAssistance(Service service, LatLng latLng) {
        GetAssistance getAssistance = new GetAssistance(service, latLng);
        threads.add(getAssistance);
        return getAssistance;
    }

    public class GetAssistance extends Thread<Alert> {

        private Service service;
        private LatLng latLng;

        public GetAssistance(Service service, LatLng latLng) {
            this.service = service;
            this.latLng = latLng;
        }

        @Override
        protected Observable<Alert> buildUseCaseObservable() {
            return repository.service().getAssistance(service, latLng);
        }
    }

    public class GetService extends Thread<List<Service>> {

        private String serviceId;

        public GetService(String serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        protected Observable<List<Service>> buildUseCaseObservable() {
            return repository.service().getServices(serviceId);
        }
    }
}
