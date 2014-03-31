package me.aktor.corsobari.app.broadcast;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by eto on 25/03/14.
 */
public class RebirthService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RebirthService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        BroadcastAwake.LOCK.release();

        BroadcastAwake.completeWakefulIntent(intent);
    }
}
