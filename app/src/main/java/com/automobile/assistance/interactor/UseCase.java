package com.automobile.assistance.interactor;

import javax.inject.Inject;

public class UseCase {

    @Inject
    UserUseCase user;

    @Inject
    LockoutUseCase lockout;

    @Inject
    TowUseCase tow;

    @Inject
    FuelUseCase fuel;

    @Inject
    TireUseCase tire;

    @Inject
    CarUseCase car;

    @Inject
    ServiceUseCase service;

    @Inject
    public UseCase() {
    }

    public UserUseCase user() {
        return user;
    }

    public LockoutUseCase lockout() {
        return lockout;
    }

    public TowUseCase tow() {
        return tow;
    }

    public CarUseCase car() {
        return car;
    }

    public FuelUseCase fuel() {
        return fuel;
    }

    public TireUseCase tire() {
        return tire;
    }

    public ServiceUseCase service() {
        return service;
    }

    public void dispose() {
        user.dispose();
        lockout.dispose();
        tow.dispose();
        fuel.dispose();
        tire.dispose();
        service.dispose();
    }

}
