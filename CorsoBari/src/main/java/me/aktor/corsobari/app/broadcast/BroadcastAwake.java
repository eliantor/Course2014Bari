package me.aktor.corsobari.app.broadcast;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by eto on 25/03/14.
 */
public class BroadcastAwake extends WakefulBroadcastReceiver {
    //static PowerManager.WakeLock LOCK;
    @Override
    public void onReceive(Context context, Intent intent) {
//        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock lock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ciao");
//        lock.acquire();
//        LOCK =lock;

        ComponentName cmp = new ComponentName(context.getPackageName(),LocationClients.class.getName());
        intent.setComponent(cmp);

        ComponentName cmpOk = startWakefulService(context, intent);

    }
}
