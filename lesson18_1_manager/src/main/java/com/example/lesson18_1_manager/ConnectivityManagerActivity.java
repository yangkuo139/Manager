package com.example.lesson18_1_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static android.net.ConnectivityManager.EXTRA_NO_CONNECTIVITY;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ConnectivityManagerActivity extends AppCompatActivity {

    ConnectivityManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //获取当前的网络状态;
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        try {
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            //判断当前的网络状态；
//            String name = activeNetworkInfo.getTypeName();
//            Log.e("TAG", "---------" + name);
            int type = activeNetworkInfo.getType();
            switch (type){
                case ConnectivityManager.TYPE_WIFI:
                    Log.e("TAG","------------使用wifi");
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    Log.e("TAG","------------使用移动数据");
                    break;
                //判断当前的网络状态;
            }
            NetworkInfo.State state = activeNetworkInfo.getState();
            if (state == NetworkInfo.State.CONNECTED){
                Log.e("TAG","------------连接状态");
            }else {
                Log.e("TAG","------------连接异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("TAG", "-------------->>" + intent.getBooleanExtra(EXTRA_NO_CONNECTIVITY, false));
        }
    };
}
