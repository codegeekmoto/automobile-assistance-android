package com.automobile.assistance.ui.client.transaction;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.automobile.assistance.R;
import com.automobile.assistance.databinding.FragmentTransactionsBinding;
import com.automobile.assistance.util.location.LocationTracker;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.jetbrains.annotations.NotNull;

public class TransactionFragment extends Fragment {

    private FragmentTransactionsBinding binding;

    private MapView mapView;
    private MapboxMap mapboxMap;
    private LocationTracker locationTracker;
    private Location location;
    private boolean isMapInit = false;

    private Bundle savedInstanceState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mapbox.getInstance(getContext(), getString(R.string.mapbox_access_token));

        binding = FragmentTransactionsBinding.inflate(inflater, container, false);

        locationTracker = new LocationTracker(getActivity(), new LocationTracker.LocationTrackerListener() {
            @Override
            public void onProviderNotAvailable() {

            }

            @Override
            public void onProviderDisabled() {

            }

            @Override
            public void onLocationChanged(Location location) {
                TransactionFragment.this.location = location;

                if (!isMapInit) {

                }

                Log.d("uuu", "Transaction fragment onLocationChanged: "+ location);
            }
        });

        return binding.getRoot();
    }

    private void setMap() {
        isMapInit = true;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                TransactionFragment.this.mapboxMap = mapboxMap;
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(8)
                        .build());

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustment
                    }
                });

            }
        });

        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("My Location"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        locationTracker.permissionResult(requestCode, permissions, grantResults);
    }
}
