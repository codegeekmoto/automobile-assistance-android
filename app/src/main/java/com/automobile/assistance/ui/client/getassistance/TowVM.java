package com.automobile.assistance.ui.client.getassistance;

import com.automobile.assistance.interactor.UseCase;

public class TowVM extends AssistanceVM {

    public TowVM(UseCase useCase) {
        super(useCase);
    }

    @Override
    protected Class<?> resolveLoggerName() {
        return TowVM.class;
    }
}