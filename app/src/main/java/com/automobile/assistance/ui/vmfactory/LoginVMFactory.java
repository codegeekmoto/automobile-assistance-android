package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.auth.login.LoginVM;

import javax.inject.Inject;

public class LoginVMFactory extends VMFactory<LoginVM> {

    @Inject
    UseCase useCase;

    @Inject
    public LoginVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return LoginVM.class;
    }

    @Override
    protected LoginVM resolveVM() {
        return new LoginVM(useCase);
    }
}
