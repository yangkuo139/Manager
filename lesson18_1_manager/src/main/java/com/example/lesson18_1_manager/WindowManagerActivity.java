package com.example.lesson18_1_manager;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class WindowManagerActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageView iv;
    WindowManager manager;
    WindowManager.LayoutParams params;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过app来获取才能做到全局，否则绑定activity。
        manager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        //获取屏幕高宽；
//        getResources().getDisplayMetrics().widthPixels;
//        getWindowManager().getDefaultDisplay().getWidth();
        //x,y;
        iv = new ImageView(this);
        iv.setOnTouchListener(this);
        iv.setImageResource(R.mipmap.ic_launcher_round);
        params = new WindowManager.LayoutParams(100, 100, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                | WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, FLAG_NOT_TOUCH_MODAL | FLAG_NOT_FOCUSABLE |
                FLAG_LAYOUT_NO_LIMITS, PixelFormat.TRANSPARENT);
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        manager.addView(iv, params);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WindowManagerActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        Log.e("TAG", "--------------" + x + "    " + y);
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //window update 方法来修改属性；
            params.x = (int) (x - 50);
            params.y = (int) (y - 50);
            manager.updateViewLayout(iv, params);
            return true;
        }
        return false;
    }
}
