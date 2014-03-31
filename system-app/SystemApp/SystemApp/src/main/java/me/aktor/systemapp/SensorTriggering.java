package me.aktor.systemapp;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.*;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

/**
 * Created by eto on 26/03/14.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SensorTriggering extends Service {
    private SensorManager mSensorManager;

    private LocationManager mLocationManager;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void monitorConstantly(){

        Criteria criteria = new Criteria();
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        Intent myBroadcast = new Intent("me.aktor.android.LOCATION_UPDATE");
        PendingIntent pi = PendingIntent.getBroadcast(this,0,myBroadcast,PendingIntent.FLAG_UPDATE_CURRENT);
        String bestProvider = mLocationManager.getBestProvider(criteria, true);
        mLocationManager.removeUpdates(PendingIntent.getActivity(this,0,null,0));
        mLocationManager.requestLocationUpdates(bestProvider,1000 /*millisec*/,20/*meters*/,pi);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    private void connectToSensorOldStyle(){
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor trigger = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        mSensorManager.registerListener(evListener,trigger,SensorManager.SENSOR_DELAY_UI);
    }

    private SensorEventListener evListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float vs =event.values[0];
            if (vs>0.5f){
                mSensorManager.unregisterListener(this);
                // sensible movement
            }
            long timestamp = event.timestamp;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void connectToSensor() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor trigger = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        if (trigger != null) {
            mSensorManager.requestTriggerSensor(eventListener, trigger);
        }
    }

    public final TriggerEventListener eventListener = new TriggerEventListener() {
        @Override
        public void onTrigger(TriggerEvent event) {
            long when=event.timestamp;
            boolean hasMoved=event.values[0]>0.5f;
        }
    };


}
