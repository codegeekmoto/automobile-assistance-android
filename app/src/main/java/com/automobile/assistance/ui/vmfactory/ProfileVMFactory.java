package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.profile.ProfileVM;

import javax.inject.Inject;

public class ProfileVMFactory extends VMFactory<ProfileVM> {

    @Inject
    UseCase useCase;

    @Inject
    public ProfileVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return ProfileVM.class;
    }

    @Override
    protected ProfileVM resolveVM() {
        return new ProfileVM(useCase);
    }
}
