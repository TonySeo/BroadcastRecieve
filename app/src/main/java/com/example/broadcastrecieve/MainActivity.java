package com.example.broadcastrecieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//    MyReceiver myReceiver;
    SmsReceiver smsReceiver;

    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
            // YES!!
            Log.i("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> YES");
        }
    }

    public void clickBtn1(View view){
        //명시적 intent receiver 실행
        Intent intent = new Intent(this, MyReceiver.class);
        sendBroadcast(intent);
        //묵시적 intentReceiver 실행
        /*Intent intent = new Intent();
        intent.setAction("aaa");
        sendBroadcast(intent);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("aaa");

        registerReceiver(myReceiver, intentFilter);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(myReceiver);
    }
}
