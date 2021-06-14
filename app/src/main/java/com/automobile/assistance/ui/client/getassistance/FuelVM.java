package com.automobile.assistance.ui.client.getassistance;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.ui.vmfactory.BaseVM;

public class FuelVM extends AssistanceVM {

    public FuelVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return FuelVM.class;
    }
}