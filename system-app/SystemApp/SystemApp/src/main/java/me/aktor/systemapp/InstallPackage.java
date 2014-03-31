package me.aktor.systemapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eto on 25/03/14.
 */
public class InstallPackage {

    public static void installApp(Context context,String url){
        InputStream downloadUrl = null;
        FileOutputStream savePath = null;
        try {
            URL serverUrl = new URL(url);
            downloadUrl =serverUrl.openStream();
            File apkFile = getApkFilePath();
            savePath = new FileOutputStream(apkFile);
            downloadTo(downloadUrl,savePath);
            promptInstall(context,apkFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(downloadUrl);
            closeSilently(savePath);
        }
    }

    public static void instalLocalApk(Context ctx){
        promptInstall(ctx,getApkFilePath());
    }


    private static void promptInstall(Context context,File f){


        
    }


    private static void closeSilently(Closeable c){
        if (c!=null){
            try {
                c.close();
            } catch (IOException e) {
                //swallow
            }
        }
    }

    private static void downloadTo(InputStream in,OutputStream out) throws IOException {
        byte[] buff = new byte[2048];
        int len=0;
        while ((len = in.read(buff))!=-1){
            out.write(buff,0,len);
        }
    }

    private static File getApkFilePath(){
        File dir = Environment.getExternalStorageDirectory();
        dir.mkdirs();
        File apkFile = new File(dir,"MyApp.apk");
        return apkFile;
    }
}
