package com.example.a660252397.boundservicedemos;

import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Context;
import android.content.ComponentName;
import android.content.ServiceConnection;

import com.example.a660252397.boundservicedemos.MyService.MyLocalBinder;


public class MainActivity extends AppCompatActivity {

    MyService myService; //reference to the class
    boolean isBound = false; //is the service bound or not
    private Button button;
    private TextView textView;
    private Binder IBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName i = startService(new Intent(this,MyService.class));

        bindService(new Intent(this, MyService.class),
                myConnection, BIND_AUTO_CREATE); //bind to service

        button = (Button) findViewById(R.id.timeButton);
        textView = (TextView) findViewById(R.id.textView);

    }

    //OnClick method for button to get time
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {
        String currentTime = myService.getCurrentTime();
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(currentTime);
    }

    //action class to bind to the service
    private ServiceConnection myConnection = new ServiceConnection() {
        //what is it you want to do when you connect?
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service; //attach binder
            myService= binder.getService(); //get service to the class
            isBound = true; //set bound to true
        }
        //this method will dictate what happens upon disconnecting the service
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


}
