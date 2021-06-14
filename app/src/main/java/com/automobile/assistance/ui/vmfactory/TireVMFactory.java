package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.getassistance.TireVM;

import javax.inject.Inject;

public class TireVMFactory extends VMFactory<TireVM> {

    @Inject
    UseCase useCase;

    @Inject
    public TireVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return TireVM.class;
    }

    @Override
    protected TireVM resolveVM() {
        return new TireVM(useCase);
    }
}
