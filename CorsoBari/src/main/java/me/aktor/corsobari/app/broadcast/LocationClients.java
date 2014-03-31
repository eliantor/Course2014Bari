package me.aktor.corsobari.app.broadcast;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 *
 * Created by Andrea Tortorella on 26/03/14.
 */
public class LocationClients extends IntentService{

    public LocationClients() {
        super(LocationClients.class.getName()
        );
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //...
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
