package com.facci.geo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.jar.Manifest;

public class GeoposicionamientoJS extends AppCompatActivity {

    LocationManager locManager;
    private double latitud;
    private double longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jd);
        //Inicializar LocManager
        locManager=(LocationManager) getSystemService(LOCATION_SERVICE);

        //Obtenr la lista de proveedores
        List<String> listaProviders= locManager.getAllProviders();

        //Obtener el primer proveedor de la lista
        LocationProvider  provider = locManager.getProvider(listaProviders.get(0));

        //
    }
    public void ActualizarLatLongClick(View v){
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.ACCES_FINE_//MODIFICAR
        )!= PackageManager.PERMISSION_GRANTED){}
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);

    }
     private final LocationListener locationListenerGPS = new LocationListener() {
         @Override
         public void onLocationChanged(Location location) {// cambia la ubicacion
             longitud=location.getLongitude();
             latitud=location.getLatitude();
             double Altitud=location.getAltitude();
             float velocidad=location.getSpeed();
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() { //MODIFICAR
                     EditText txtLongitud= (EditText)findViewById(R.id.txtLongitud);
                     EditText txtLatitud= (EditText)findViewById(R.id.txtLatitud);

                     txtLatitud.setText(latitud + "");
                     txtLongitud.setText(longitud+"");
                 }
             });
         }

         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) { //cambia la presicion

         }

         @Override
         public void onProviderEnabled(String provider) {

         }

         @Override
         public void onProviderDisabled(String provider) {

         }
     };
}
