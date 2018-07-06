package pe.edu.pucp.examen2.Vista.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.examen2.Modelo.Usuario;
import pe.edu.pucp.examen2.Presentador.IUsuarioPresenter;
import pe.edu.pucp.examen2.Presentador.UsuarioPresenter;
import pe.edu.pucp.examen2.R;
import pe.edu.pucp.examen2.Vista.MainActivity;
import pe.edu.pucp.examen2.Vista.Pantalla2Activity;

public class Pantalla2Fragmento extends Fragment  implements OnMapReadyCallback, IPantalla3 {
    private GoogleMap mMap;

    private Button botonSolmenly;
    private LocationManager locationManager;
    private IUsuarioPresenter iUsuarioPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        iUsuarioPresenter = new UsuarioPresenter(null);
        //mUserId = args.getString("userId", "");

    }

    public static Pantalla2Fragmento getNewInstance() {
        Pantalla2Fragmento f = new Pantalla2Fragmento();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("userId", "");
        f.setArguments(args);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_pantalla2, container, false);

        botonSolmenly = (Button) rootView.findViewById(R.id.botonMischief);
        botonSolmenly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        iUsuarioPresenter = new UsuarioPresenter(null);

//        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(rootView.getContext());
//
//        if (status == ConnectionResult.SUCCESS) {
//            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
//        } else {
//            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) rootView.getContext(), 10);
//            dialog.show();
//        }
//
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(rootView.getContext());



        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng1Actual =  new LatLng(location.getLatitude(),location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1Actual));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
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
    public void limpiar() {
        mMap.clear();
    }

    @Override
    public void añadirUsuarios(List<Usuario> listaUsuarios) {

        for(Usuario usuario:listaUsuarios){
            if(usuario.getPosicion() != null && usuario.getPosicion().size()==2){
                System.out.println("Usuario con posiciones");
                LatLng point = new LatLng(usuario.getPosicion().get(0),usuario.getPosicion().get(1));
                MarkerOptions markerActual = new MarkerOptions().position(point);
                mMap.addMarker(markerActual);
            }
        }
    }

    @Override
    public void finalizarRefresh() {

    }
//
//    @Override
//    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.botonMischief:
//                Intent intent = new Intent(view.getContext(),MainActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }
}
