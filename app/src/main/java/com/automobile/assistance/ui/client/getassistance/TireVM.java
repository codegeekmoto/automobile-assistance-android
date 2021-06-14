package com.automobile.assistance.ui.client.getassistance;


import com.automobile.assistance.interactor.UseCase;

public class TireVM extends AssistanceVM {

    public TireVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return TireVM.class;
    }
}