package com.automobile.assistance.ui.client.getassistance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.automobile.assistance.R;
import com.automobile.assistance.app.Constant;
import com.automobile.assistance.data.remote.pojo.Alert;
import com.automobile.assistance.data.remote.pojo.Service;
import com.automobile.assistance.databinding.ItemConfirmationBinding;
import com.automobile.assistance.databinding.ItemServicesBinding;
import com.automobile.assistance.otto.AssistanceEvent;
import com.automobile.assistance.util.ResultObserver;
import com.automobile.assistance.util.location.LocationTracker;
import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AssistanceFragment extends Fragment implements LocationTracker.LocationTrackerListener {

    Logger LOG = LoggerFactory.getLogger(AssistanceFragment.class);

    protected MapView mapView;
    protected MapboxMap mapboxMap;
    protected LocationTracker locationTracker;
    protected Location location;
    protected boolean isMapInit = false;
    private List<Service> services;

    private Bundle savedInstanceState;

    private AlertDialog alertDialog;
    private AlertDialog detailDialog;

    protected abstract RecyclerView serviceList();
    protected abstract AssistanceVM getVm();
    protected abstract String getServiceId();
    protected void onMapReady() {};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), Constant.MAP_BOX_ACCESS_TOKEN);//getString(R.string.mapbox_access_token));
    }

    // Map box code

    protected void initMap(MapView map) {
        this.mapView = map;
        // locationTracker = new LocationTracker(getActivity(), this);

        setMap();
    }

    private void setMap() {
        isMapInit = true;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                AssistanceFragment.this.mapboxMap = mapboxMap;
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

                fetchServices(getServiceId());

                AssistanceFragment.this.onMapReady();

            }
        });
    }

    private void showService(int id) {

        double random = ThreadLocalRandom.current().nextDouble(40, 70);

        Log.d("eee", "showService: "+ String.format("%.2f", random));

        for (Service temp: services) {
            if (temp.getCompanyServiceId() == id) {

                ItemServicesBinding binding = ItemServicesBinding.inflate(LayoutInflater.from(getContext()), null, false);
                // LatLng from = new LatLng(location.getLatitude(), location.getLongitude());
                LatLng from = new LatLng(11.244711, 125.003546);
                LatLng to = new LatLng(temp.getLatlng().getLat(), temp.getLatlng().getLng());

                binding.name.setText(temp.getDescription());
                binding.address.setText(temp.getAddress());
                String distance = String.valueOf(Math.round(from.distanceTo(to) / 1000)) + "km";
                binding.distance.setText(distance);

                if (getServiceId().equals("2")) {
                    binding.premium.setText("Premium - "+
                            String.format("%.2f", ThreadLocalRandom.current().nextDouble(50, 60)) +"/L"
                    );

                    binding.unleaded.setText("Unleaded - "+
                            String.format("%.2f", ThreadLocalRandom.current().nextDouble(50, 60)) +"/L"
                    );

                    binding.diesel.setText("Diesel - "+
                            String.format("%.2f", ThreadLocalRandom.current().nextDouble(40, 50)) +"/L"
                    );

                    binding.kerosene.setText("Kerosene - "+
                            String.format("%.2f", ThreadLocalRandom.current().nextDouble(50, 80)) +"/L"
                    );

                    binding.price.setVisibility(View.VISIBLE);
                    binding.btnAssist.setVisibility(View.GONE);
                }

                binding.btnAssist.setOnClickListener(v -> {

                    // Get assistance
                    getVm().getAssistance(temp, from);

                    ItemConfirmationBinding bindings = ItemConfirmationBinding.inflate(LayoutInflater.from(getContext()), null, false);


                    alertDialog = new AlertDialog.Builder(getContext())
                            .setView(bindings.getRoot())
                            .setTitle("")
                            .setCancelable(false)
                            .create();

                    bindings.btnCancel.setOnClickListener(view -> {
                        alertDialog.dismiss();
                    });

                    alertDialog.show();
                });

                detailDialog = new AlertDialog.Builder(getContext())
                        .setView(binding.getRoot())
                        .setCancelable(false)
                        .create();

                binding.btnClose.setOnClickListener(v -> {
                    detailDialog.dismiss();
                });

                detailDialog.show();

//                MapboxDirections client = MapboxDirections.builder()
//                        .origin(Point.fromLngLat(latLng))
//                        .destination(destination)
//                        .overview(DirectionsCriteria.OVERVIEW_FULL)
//                        .profile(DirectionsCriteria.PROFILE_DRIVING)
//                        .accessToken(Constant.MAP_BOX_ACCESS_TOKEN)
//                        .build();

                break;
            }
        }
    }

    public void onMessage(AssistanceEvent event) {
        String type = event.getType();
        String msg = "";
        boolean isAccepted = false;

        if (type.equals("assistance-refused")) {
            msg = "Your assistance request had been refused.";
            isAccepted = false;
        } else {
            msg = "Your assistance request had been accepted.";
            isAccepted = true;
        }

        new AlertDialog.Builder(getContext())
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                        detailDialog.dismiss();

                        getVm().saveRequestedAssistance(true);
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                                .navigate(R.id.nav_transaction);
                    }
                })
                .create()
                .show();
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
                AssistanceFragment.this.services = services;

                for (Service service: services) {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(new LatLng(service.getLatlng().getLat(), service.getLatlng().getLng()))
                            .title(service.getCompanyServiceId().toString());

                    mapboxMap.addMarker(markerOptions);
                }

                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull @NotNull Marker marker) {
                        if (marker.getTitle() != "My Location") {
                            showService(Integer.parseInt(marker.getTitle()));
                        }
                        return false;
                    }
                });
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

        getVm().getAssistanceResult().observe(getViewLifecycleOwner(), new ResultObserver<Alert>() {
            @Override
            public void onSuccess(Alert alert) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
