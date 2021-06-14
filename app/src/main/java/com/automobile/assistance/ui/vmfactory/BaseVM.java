package com.automobile.assistance.ui.vmfactory;

import androidx.lifecycle.ViewModel;

import com.automobile.assistance.interactor.UseCase;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;

public abstract class BaseVM extends ViewModel {

    protected Logger LOG;

    protected UseCase useCase;

    protected BaseVM(UseCase useCase) {
        this.useCase = useCase;
        LOG = LoggerFactory.getLogger(resolveLoggerName());
    }

    protected abstract Class<?> resolveLoggerName();

    @Override
    protected void onCleared() {
        super.onCleared();
        useCase.dispose();
    }

    protected void printError(Throwable e) {
        LOG.error(e.getMessage());
        e.printStackTrace();
    }
}
