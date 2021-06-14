package com.automobile.assistance.util.location;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.LOCATION_SERVICE;

public class LocationTracker implements LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    private Activity activity;
    private LocationManager locationManager;
    private LocationTrackerListener locationTrackerListener;

    boolean checkGPS = false;
    boolean checkNetwork = false;

    private Location location;
    double latitude;
    double longitude;

    public LocationTracker(Activity activity, LocationTrackerListener locationTrackerListener) {
        this.activity = activity;
        this.locationTrackerListener = locationTrackerListener;

        locationManager = (LocationManager) activity
                .getSystemService(LOCATION_SERVICE);

        // get GPS status
        checkGPS = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // get network provider status
        checkNetwork = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!checkGPS && !checkNetwork) {
            new AlertDialog.Builder(activity)
                    .setTitle("")
                    .setMessage("No location service provider is available. Please enable your device's GPS.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            locationTrackerListener.onProviderNotAvailable();
                        }
                    })
                    .create()
                    .show();
        }

        if (checkGPS) {
            trackLocation();
        }
    }

    public void trackLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (locationManager != null) {
                location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        }
    }

    public void permissionResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            trackLocation();
        } else {
            // checkPermission();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
            }
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.location = location;
        locationTrackerListener.onLocationChanged(location);

        Log.d("uuu", "onLocationChanged: "+ location);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        new AlertDialog.Builder(activity)
                .setTitle("")
                .setMessage("Location Service Provider has been disabled.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        locationTrackerListener.onProviderDisabled();
                    }
                })
                .create()
                .show();
    }

    public interface LocationTrackerListener {

        void onProviderNotAvailable();
        void onProviderDisabled();
        void onLocationChanged(Location location);
    }
}
