package com.automobile.assistance.ui.vmfactory;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.client.getassistance.TransactionVM;

import javax.inject.Inject;

public class TransactionVMFactory extends VMFactory<TransactionVM> {

    @Inject
    UseCase useCase;

    @Inject
    public TransactionVMFactory() {
    }

    @Override
    protected Class<?> getVmClass() {
        return TransactionVM.class;
    }

    @Override
    protected TransactionVM resolveVM() {
        return new TransactionVM(useCase);
    }
}
