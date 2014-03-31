package me.aktor.systemapp;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;

import java.util.List;

/**
 * Created by eto on 25/03/14.
 */
public class BroadcastConnectivityListener extends BroadcastReceiver {
    public static final String LTAG = "BCS_REC";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())){
            Log.d(LTAG,"Action received");
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            if (!wifiManager.isWifiEnabled()){
                try {
                    int state = Settings.Global.getInt(context.getContentResolver(), Settings.Global.WIFI_ON);
                    wifiManager.setWifiEnabled(true);
                    Log.d(LTAG, "State changed " + state);
                } catch (Settings.SettingNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if (LocationManager.PROVIDERS_CHANGED_ACTION.equals(intent.getAction())){
            Log.d(LTAG,"GPS STATUS CHANGED");
            LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
            boolean gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.d(LTAG,"GPS IS enabled? "+gpsEnabled);
            if (!gpsEnabled){
                try {
                    int state =Settings.Secure.getInt(context.getContentResolver(),Settings.Secure.LOCATION_MODE);
                    if (state!=Settings.Secure.LOCATION_MODE_HIGH_ACCURACY){
                        Settings.Secure.putInt(context.getContentResolver(),
                                Settings.Secure.LOCATION_MODE,
                                Settings.Secure.LOCATION_MODE_HIGH_ACCURACY);
                    }
                    Log.d(LTAG,"Gps state is:"+state);
                } catch (Settings.SettingNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
