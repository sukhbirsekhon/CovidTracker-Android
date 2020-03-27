package com.example.localevents;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.security.ProtectionDomain;



public class GPSTracker implements LocationListener
{
    double longitute;
    double latitude;

    @Override
    public void onLocationChanged(Location location) {
        if(location!= null)
        {
            longitute = location.getLongitude();
            latitude = location.getLatitude();
            System.out.println("oooooooooooooooooooooooo" + longitute);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa" + latitude);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public double getLongitute()
    {
        Location location = null;
        longitute = location.getLongitude();
        return longitute;
    }

}
