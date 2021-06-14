package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.mycar.CarVM;

import javax.inject.Inject;

public class CarVMFactory extends VMFactory<CarVM> {

    @Inject
    UseCase useCase;

    @Inject
    public CarVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return CarVM.class;
    }

    @Override
    protected CarVM resolveVM() {
        return new CarVM(useCase);
    }
}
