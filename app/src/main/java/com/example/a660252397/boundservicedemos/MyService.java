package com.example.a660252397.boundservicedemos;

import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    //create an object this is going to be the binder
    private final IBinder myBinder = new MyLocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("MM:mm:ss", Locale.US);
        return df.format(new Date());
    }

    //a class that has the ability to bind
    public class MyLocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }



}