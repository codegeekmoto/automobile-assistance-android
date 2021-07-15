package com.automobile.assistance.app;

import android.app.Application;

import com.automobile.assistance.app.dagger.AppComponent;
import com.automobile.assistance.app.dagger.ContextModule;
import com.automobile.assistance.app.dagger.DaggerAppComponent;
import com.automobile.assistance.data.Repository;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Inject;

public class App extends Application {

    private Logger LOG = LoggerFactory.getLogger(App.class);

    private static App INSTANCE;
    private static AppComponent COMPONENT;
    private static Bus eventBus;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        COMPONENT = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        eventBus = new Bus(ThreadEnforcer.ANY);

        COMPONENT.inject(this);
    }

    public Repository repository() {
        return repository;
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public static AppComponent getComponent() {
        return COMPONENT;
    }

    public static Bus getEventBus() {
        return eventBus;
    }
}
