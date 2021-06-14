package com.automobile.assistance.app.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(Constant.APP_NAME, 0);
    }

    @Provides
    @Singleton
    Prefs providePrefs(SharedPreferences sharedPreferences) {
        return new Prefs(sharedPreferences);
    }

    @Provides
    @Singleton
    Database provideDatabase(Context context) {
        return new Database(context);
    }

}
