package com.automobile.assistance.interactor;

import com.automobile.assistance.data.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TowUseCase extends UseCaseWrapper {

    @Inject
    Repository repository;

    private List<Thread> threads = new ArrayList<>();

    @Inject
    public TowUseCase() {
    }
}
