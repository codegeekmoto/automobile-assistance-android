package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.auth.register.RegisterVM;

import javax.inject.Inject;

public class RegisterVMFactory extends VMFactory<RegisterVM> {

    @Inject
    UseCase useCase;

    @Inject
    public RegisterVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return RegisterVM.class;
    }

    @Override
    protected RegisterVM resolveVM() {
        return new RegisterVM(useCase);
    }
}
