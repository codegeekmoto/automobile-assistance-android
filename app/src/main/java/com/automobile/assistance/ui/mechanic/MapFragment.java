package com.automobile.assistance.ui.mechanic;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Transaction;
import com.automobile.assistance.databinding.FragmentMapBinding;
import com.automobile.assistance.ui.client.getassistance.AssistanceFragment;
import com.automobile.assistance.util.location.LocationTracker;
import com.google.gson.Gson;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    // Mapbox stuff
    protected MapView mapView;
    protected MapboxMap mapboxMap;
    protected LocationTracker locationTracker;
    protected Location location;
    protected boolean isMapInit = false;

    private Transaction transaction;

    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Mapbox.getInstance(getContext(), Constant.MAP_BOX_ACCESS_TOKEN);
        binding = FragmentMapBinding.inflate(inflater, container, false);

        transaction = new Gson().fromJson(
                getArguments().getString("transaction"),
                Transaction.class
        );

        this.mapView = binding.map;
        setMap(savedInstanceState);

        return binding.getRoot();
    }

    private void setMap(Bundle savedInstanceStat) {
        isMapInit = true;
        mapView.onCreate(savedInstanceStat);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                MapFragment.this.mapboxMap = mapboxMap;
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

                LatLng clintLoc = new LatLng(transaction.getClientLoc().getLat(), transaction.getClientLoc().getLng());
                mapboxMap.addMarker(new MarkerOptions()
                        .position(clintLoc)
                        .title(transaction.getClientFname() + " " + transaction.getClientLname()));

                binding.progressLayout.parent.setVisibility(View.GONE);
            }
        });
    }
}