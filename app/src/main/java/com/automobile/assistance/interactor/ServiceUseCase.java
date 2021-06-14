package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.Service22;

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
