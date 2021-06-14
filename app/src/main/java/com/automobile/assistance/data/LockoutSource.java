package com.automobile.assistance.data;

import com.automobile.assistance.data.local.Prefs;
import com.automobile.assistance.data.local.db.Database;
import com.automobile.assistance.data.remote.RemoteDataApi;

import javax.inject.Inject;

public class LockoutSource extends DataSource {


    @Inject
    public LockoutSource(Prefs prefsApi, RemoteDataApi remoteApi, Database dbApi) {
        super(prefsApi, remoteApi, dbApi);
    }

    public void insert() {

    }
}
