package com.automobile.assistance.app;

import android.app.Application;

import com.automobile.assistance.app.dagger.AppComponent;
import com.automobile.assistance.app.dagger.ContextModule;
import com.automobile.assistance.app.dagger.DaggerAppComponent;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;

public class App extends Application {

    private Logger LOG = LoggerFactory.getLogger(App.class);

    private static App INSTANCE;
    private static AppComponent COMPONENT;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        COMPONENT = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public static AppComponent getComponent() {
        return COMPONENT;
    }
}
