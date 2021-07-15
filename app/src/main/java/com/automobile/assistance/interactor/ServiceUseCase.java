package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;
import com.automobile.assistance.data.ServiceSource;
import com.automobile.assistance.data.remote.pojo.Alert;
import com.automobile.assistance.data.remote.pojo.Jobs;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.Service22;
import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.data.remote.pojo.User;
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

    public ServiceSource source() {
        return repository.service();
    }

    public GetTransactions getTransactions(String userId, String type) {
        GetTransactions getTransactions = new GetTransactions(userId, type);
        threads.add(getTransactions);
        return getTransactions;
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

    public CompleteJob completeJob(String jobId) {
        CompleteJob completeJob = new CompleteJob(jobId);
        threads.add(completeJob);
        return completeJob;
    }

    public GetMechanics getMechanics() {
        GetMechanics getMechanics = new GetMechanics();
        threads.add(getMechanics);
        return getMechanics;
    }

    public class CompleteJob extends Thread<Boolean> {

        String jobId;

        public CompleteJob(String jobId) {
            this.jobId = jobId;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            return repository.service().completeJob(jobId);
        }
    }

    public class GetMechanics extends Thread<List<User>> {

        @Override
        protected Observable<List<User>> buildUseCaseObservable() {
            return repository.service().getMechanics();
        }
    }

    public class GetTransactions extends Thread<Jobs> {

        String userId;
        String type;

        public GetTransactions(String userId, String type) {
            this.userId = userId;
            this.type = type;
        }

        @Override
        protected Observable<Jobs> buildUseCaseObservable() {
            return repository.service().getTransaction(type, userId);
        }
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
