package me.aktor.systemapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

/**
 * Created by eto on 26/03/14.
 */
public class BroadcastUpload extends BroadcastReceiver{
    public static PowerManager.WakeLock WL;
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        WL = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"boj");
        WL.acquire();
        context.startService(new Intent(context,MyUploader.class));
    }

}
