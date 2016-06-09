package com.example.alu201416282.healthymeal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import android.location.LocationListener;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Marker lastLocation;
    private LatLng current = new LatLng(0,0);
    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        ArrayList<Restaurante> lista = new ArrayList<>();
        lista.add(new Restaurante("Bistro Garni","-30.031647","-51.209851","(51)3095-2407","Rua Felipe Camarão, numero 265","",false,true,false,false));
        lista.add(new Restaurante("Cafe da Paleta","-30.038166","-51.211188","(51)3012-5833","Rua Venancio Aires, numero 964","",true,true,false,false));
        lista.add(new Restaurante("Bife Hamburgueria","-30.031449","-51.205901","(51)3072-8033","Rua Miguel Tostes, numero 371","",false,true,false,false));


        Database.createDatabase();
        Database.addRestaurantes(lista);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Restaurante> lista = Database.getRestaurantes();

        for(int i=0;i<lista.size();i++){
            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lista.get(i).latitude),Double.parseDouble(lista.get(i).longitude))).title("You are here").icon(BitmapDescriptorFactory.fromResource(R.mipmap.current)));
        }

        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location) {
                LatLng current = new LatLng(location.getLatitude(),location.getLongitude());
                if(lastLocation!=null)
                    lastLocation.remove();
                lastLocation = mMap.addMarker(new MarkerOptions().position(current).title("You are here").icon(BitmapDescriptorFactory.fromResource(R.mipmap.current)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current,(float)16.0));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, locationListener);

    }

}
