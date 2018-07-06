package pe.edu.pucp.examen2.Vista;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pe.edu.pucp.examen2.Modelo.Usuario;
import pe.edu.pucp.examen2.Presentador.IUsuarioPresenter;
import pe.edu.pucp.examen2.Presentador.UsuarioPresenter;
import pe.edu.pucp.examen2.R;

public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    private Button botonSolmenly;
    private LocationManager locationManager;
    private IUsuarioPresenter iUsuarioPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        botonSolmenly = (Button) findViewById(R.id.botonSolemnly);
        botonSolmenly.setOnClickListener(this);

        iUsuarioPresenter = new UsuarioPresenter(null);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            System.out.println("Connection lost");
//            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
//            dialog.show();
        }


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng1Actual =  new LatLng(location.getLatitude(),location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1Actual));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                String keyUsuario= "-LGlkp0jGETJY-Oc0XJ0";
                iUsuarioPresenter.enviarDatosUsuario(getApplicationContext(),28.0289837,1.6666663,keyUsuario);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,locationListener);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonSolemnly:
                Intent intent = new Intent(getApplicationContext(),Pantalla2Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    // setear posiciones a 0

    }
}
