package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.getassistance.LockoutVM;

import javax.inject.Inject;

public class LockoutVMFactory extends VMFactory<LockoutVM> {

    @Inject
    UseCase useCase;

    @Inject
    public LockoutVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return LockoutVM.class;
    }

    @Override
    protected LockoutVM resolveVM() {
        return new LockoutVM(useCase);
    }
}
