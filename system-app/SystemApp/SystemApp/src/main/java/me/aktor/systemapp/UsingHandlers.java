package me.aktor.systemapp;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by eto on 26/03/14.
 */
public class UsingHandlers {

    private Handler mHandler;

    private static class MtHandler extends Handler{

        public MtHandler(Looper l){
            super(l);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    public void useHandlers(){
        mHandler = new MtHandler(Looper.getMainLooper());
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        Message m =mHandler.obtainMessage(1,"ciao");
        mHandler.sendMessage(m);
    }
}
