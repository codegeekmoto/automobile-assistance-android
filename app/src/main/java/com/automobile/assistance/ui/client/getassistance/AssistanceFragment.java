package com.automobile.assistance.ui.client.getassistance;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.R;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.data.remote.pojo.Service22;
import com.automobile.assistance.ui.adapter.ServiceAdapter;
import com.automobile.assistance.util.ResultObserver;
import com.automobile.assistance.util.location.LocationTracker;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AssistanceFragment extends Fragment implements LocationTracker.LocationTrackerListener {

    Logger LOG = LoggerFactory.getLogger(AssistanceFragment.class);

    protected MapView mapView;
    protected MapboxMap mapboxMap;
    protected LocationTracker locationTracker;
    protected Location location;
    protected boolean isMapInit = false;

    private Bundle savedInstanceState;

    protected abstract RecyclerView serviceList();
    protected abstract AssistanceVM getVm();
    protected abstract String getServiceId();
    protected void onMapReady() {};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), "pk.eyJ1IjoianVuZnJlZWNzcyIsImEiOiJja3B0bzhla3kwNHl0MnhwOTN6ZGpxZHZ5In0.bk2iOlhxIwYNEKhcS6IasA");//getString(R.string.mapbox_access_token));
    }

    // Map box code

    protected void initMap(MapView map) {
        this.mapView = map;
        locationTracker = new LocationTracker(getActivity(), this);
    }

    private void setMap() {
        isMapInit = true;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                AssistanceFragment.this.mapboxMap = mapboxMap;
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

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("My Location"));

                AssistanceFragment.this.onMapReady();

                fetchServices("");
            }
        });
    }

    @Override
    public void onProviderNotAvailable() {
        getActivity().onBackPressed();
    }

    @Override
    public void onProviderDisabled() {

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
         if (!isMapInit) {
             setMap();
         }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        locationTracker.permissionResult(requestCode, permissions, grantResults);
    }

    // End map box code

    protected void fetchServices(String id) {
        getVm().getServiceResult().observe(getViewLifecycleOwner(), new ResultObserver<List<Service>>() {
            @Override
            public void onSuccess(List<Service> services) {
                for (Service service: services) {
                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(service.getLatlng().getLat(), service.getLatlng().getLng()))
                            .title(service.getDescription()));
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        });

        getVm().fetchService(getServiceId());
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
}
