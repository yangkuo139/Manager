package com.example.lesson18_1_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        btn.setOnClickListener(this);
        btn.setText("开启闹钟");
        setContentView(btn);
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog dialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);
        dialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //时间到了之后，开启一个闹钟；
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //开启一个全局定时器；
        Calendar calendar = Calendar.getInstance();
        Log.e("TAG", "--------------->>" + hourOfDay + " " + minute);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
        Log.e("TAG","---------->>"+calendar.getTime().getTime()+"   "+ System.currentTimeMillis());
        Intent intent = new Intent(this,AudioManagerActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager.set(AlarmManager.RTC,calendar.getTime().getTime(),pi);
//        manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTime().getTime(),0,pi);

    }
}
