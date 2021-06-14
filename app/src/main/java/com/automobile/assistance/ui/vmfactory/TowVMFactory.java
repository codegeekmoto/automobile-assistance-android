package com.automobile.assistance.ui.vmfactory;

import androidx.lifecycle.ViewModel;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.getassistance.TowVM;

import javax.inject.Inject;

public class TowVMFactory extends VMFactory<TowVM> {

    @Inject
    UseCase useCase;

    @Inject
    public TowVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return TowVM.class;
    }

    @Override
    protected TowVM resolveVM() {
        return new TowVM(useCase);
    }
}
