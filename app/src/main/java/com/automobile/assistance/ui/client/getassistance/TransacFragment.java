package com.automobile.assistance.ui.client.getassistance;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.R;
import com.automobile.assistance.app.Constant;
import com.automobile.assistance.databinding.FragmentTransacBinding;
import com.automobile.assistance.util.location.LocationTracker;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class TransacFragment extends Fragment {

    private FragmentTransacBinding binding;


    protected MapView mapView;
    protected MapboxMap mapboxMap;
    protected LocationTracker locationTracker;
    protected Location location;
    protected boolean isMapInit = false;


    public TransacFragment() {
        // Required empty public constructor
    }


    public static TransacFragment newInstance() {
        return  new TransacFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Mapbox.getInstance(getContext(), Constant.MAP_BOX_ACCESS_TOKEN);
        binding = FragmentTransacBinding.inflate(inflater, container, false);

        this.mapView = binding.map;
        setMap(savedInstanceState);

        return binding.getRoot();
    }

    private void setMap(Bundle savedInstanceState) {
        isMapInit = true;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                TransacFragment.this.mapboxMap = mapboxMap;
                // LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                LatLng latLng = new LatLng(11.244711, 125.003546);

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
                        .position(latLng)
                        .title("My Location"));

                binding.progressLayout.parent.setVisibility(View.GONE);
            }
        });
    }
}