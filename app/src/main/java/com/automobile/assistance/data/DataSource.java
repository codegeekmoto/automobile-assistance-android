package com.automobile.assistance.data;

import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;

public abstract class DataSource {

    protected Prefs prefsApi;
    protected RemoteDataApi remoteApi;
    protected Database dbApi;

    protected DataSource(Prefs prefsApi, RemoteDataApi remoteApi, Database dbApi) {
        this.prefsApi = prefsApi;
        this.remoteApi = remoteApi;
        this.dbApi = dbApi;
    }

}
