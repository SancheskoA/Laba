package com.example.pr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng Cheez = new LatLng(56.009991500900945, 92.80091209409862);
        LatLng PapaJones = new LatLng(56.016743868788105, 92.80116276577486);
        LatLng NinjaPizza = new LatLng(56.023075388671764, 92.80767438893146);
        LatLng DodoPizza = new LatLng(56.01503360511616, 92.81479339062025);
        LatLng Subito = new LatLng(56.02069403082316, 92.83834920841794);


        map.addMarker(new MarkerOptions().position(Cheez).title("Cheez"));
        map.addMarker(new MarkerOptions().position(PapaJones).title("Papa Jones"));
        map.addMarker(new MarkerOptions().position(NinjaPizza).title("Ninja Pizza"));
        map.addMarker(new MarkerOptions().position(DodoPizza).title("Dodo Pizza"));
        map.addMarker(new MarkerOptions().position(Subito).title("Subito"));

        map.moveCamera(CameraUpdateFactory.newLatLng(Cheez));

    }
}