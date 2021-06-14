package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;

import javax.inject.Inject;

public class FuelUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    @Inject
    public FuelUseCase() {
    }
}
