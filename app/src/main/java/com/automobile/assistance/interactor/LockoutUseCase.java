package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class LockoutUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    @Inject
    public LockoutUseCase() {
    }

    public AddTow insert() {
        AddTow addTow = new AddTow(repository);
        threads.add(addTow);
        return addTow;
    }

    public class AddTow extends Thread<Boolean> {

        private Repository repository;

        public AddTow(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected Observable<Boolean> buildUseCaseObservable() {
            // return repository.lockout().insert();
            return null;
        }
    }
}
