package com.julioprojects.mypetcare.Home;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.julioprojects.mypetcare.R;
import com.julioprojects.mypetcare.databinding.ActivityMapsBinding;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng petStop = new LatLng(-20.5397898, -47.4168331);
        mMap.addMarker(new MarkerOptions().position(petStop).title("Parada do Pet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(petStop));

        LatLng pet1 = new LatLng(-20.534994, -47.4256432);
        mMap.addMarker(new MarkerOptions().position(pet1).title("Agro Pet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pet1));

        LatLng pet2 = new LatLng(-20.5187751, -47.4604009);
        mMap.addMarker(new MarkerOptions().position(pet2).title("Pet Di Paula"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pet2));

        LatLng pet3 = new LatLng(-20.5427524, -47.4495134);
        mMap.addMarker(new MarkerOptions().position(pet3).title("Vila Vet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pet3));
    }
}