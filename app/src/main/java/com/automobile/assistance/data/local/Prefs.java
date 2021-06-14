package com.automobile.assistance.data.local;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void setObject(String key, Object object) {
        preferences.edit()
                .putString(key, new Gson().toJson(object))
                .apply();
    }

    public <T> T getObject(String key, Class<T> objClass) {
        return new Gson().fromJson(preferences.getString(key, ""), objClass);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
