package com.automobile.assistance.data;

import javax.inject.Inject;

public class Repository {

    @Inject
    UserSource userSource;

    @Inject
    LockoutSource lockoutSource;

    @Inject
    TowSource towSource;

    @Inject
    FuelSource fuelSource;

    @Inject
    TireSource tireSource;

    @Inject
    CarSource carSource;

    @Inject
    ServiceSource serviceSource;

    @Inject
    public Repository() {}

    public UserSource user() {
        return userSource;
    }

    public LockoutSource lockout() {
        return lockoutSource;
    }

    public TowSource tow() {
        return towSource;
    }

    public FuelSource fuel() {
        return fuelSource;
    }

    public TireSource tire() {
        return tireSource;
    }

    public CarSource car() {
        return carSource;
    }

    public ServiceSource service() { return  serviceSource; }
}
