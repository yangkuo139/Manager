package com.example.lesson18_1_manager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class SensorManagerActivity extends AppCompatActivity {

    SensorManager sensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //判断，当前的手机有哪些传感器。
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.e("TAG","-------------"+sensorList.size());
        for (Sensor sensor : sensorList) {
            Log.e("TAG","--------------"+sensor.getName());
        }
        //获取任意一款传感器；
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(sensor !=null){
            //所有的传感器，都是使用监听来获取数据的
            //多久回传一次数据：
            sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器回传数据
            //x y z   光线，临近，温度等等 都只有一个值
            float[] values = event.values;
            Log.e("TAG","----------------"+ Arrays.toString(values));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //当周期改变的时候
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一定要销毁；
        sensorManager.unregisterListener(listener);
    }
}
