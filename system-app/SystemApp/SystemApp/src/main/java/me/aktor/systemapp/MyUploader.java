package me.aktor.systemapp;

import android.app.IntentService;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.IBinder;

import java.net.HttpURLConnection;
/**
 * Created by eto on 26/03/14.
 */
public class MyUploader extends IntentService {
    public static final String THREAD_NAME = MyUploader.class.getName();


    public MyUploader() {
        super(THREAD_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate(); // obbligatorio

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        BroadcastUpload.WL.release();
    }
}
