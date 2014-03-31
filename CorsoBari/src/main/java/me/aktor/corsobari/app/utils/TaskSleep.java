package me.aktor.corsobari.app.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import me.aktor.corsobari.app.ActivityEdit;

import java.lang.ref.WeakReference;

/**
 * Created by eto on 25/03/14.
 */
public class TaskSleep extends AsyncTask<Long,Void,Long> {
    Context ctx;
   // private final WeakReference<Context> mActivityRef;

    public TaskSleep(Context e){
        ctx = e.getApplicationContext();
    }

    @Override
    protected Long doInBackground(Long... params) {
        try {
            Thread.sleep(params[0].longValue());
        } catch (InterruptedException e) {
            return -1l;
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(Long aLong) {
        //final Context activity = mActivityRef.get();
//        if (activity != null) {
//            Toast.makeText(activity, "Ho dormito un po: " + aLong, Toast.LENGTH_LONG).show();
//        }
    }
}
