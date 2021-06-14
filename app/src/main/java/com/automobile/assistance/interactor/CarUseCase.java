package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class CarUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    @Inject
    public CarUseCase() {}

//    public Insert insert(Car car) {
//        Insert insert = new Insert(car);
//        threads.add(insert);
//        return insert;
//    }
//
//    public MyCar myCars() {
//        MyCar myCar = new MyCar();
//        threads.add(myCar);
//        return myCar;
//    }
//
//    public class Insert extends Thread<Car> {
//
//        private Car car;
//
//        public Insert(Car car) {
//            this.car = car;
//        }
//
//        @Override
//        protected Observable<Car> buildUseCaseObservable() {
//            return repository.car().insert(car);
//        }
//    }
//
//    public class MyCar extends Thread<List<Car>> {
//
//        @Override
//        protected Observable<List<Car>> buildUseCaseObservable() {
//            return repository.car().myCar();
//        }
//    }

}
