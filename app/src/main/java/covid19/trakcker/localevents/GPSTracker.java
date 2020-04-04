package covid19.trakcker.localevents;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


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
