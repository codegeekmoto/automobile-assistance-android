package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.getassistance.FuelVM;

import javax.inject.Inject;

public class FuelVMFactory extends VMFactory<FuelVM> {

    @Inject
    UseCase useCase;

    @Inject
    public FuelVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return FuelVM.class;
    }

    @Override
    protected FuelVM resolveVM() {
        return new FuelVM(useCase);
    }
}
