package me.aktor.corsobari.app.broadcast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import me.aktor.corsobari.app.MainActivity;
import me.aktor.corsobari.app.R;

/**
 * Created by eto on 25/03/14.
 */
public class NewMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main);
        findViewById(R.id.dowork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAfter(4000);
            }
        });

    }

    private void acquireWakelock(){
        PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);
        PowerManager.WakeLock lock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "mywalelock");
        lock.acquire();

        lock.release();

    }
    private PendingIntent makeIntent(){
        Intent i  = new Intent(this,MainActivity.class);
        PendingIntent delega  = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        return delega;
    }

    private void startAfter(long millis){
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, millis, millis, makeIntent());
    }
}
