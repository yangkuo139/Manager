package com.example.lesson18_1_manager;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class AudioManagerActivity extends AppCompatActivity {

    //获取音频相关的信息；
    AudioManager manager;
    SeekBar sb;
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.sb);
        tv = (TextView) findViewById(R.id.tv);
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //好几种 声音的类型
        //获取 音乐的 声音的最大值
        int streamMaxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(streamMaxVolume);
        //获取的当前的 音乐的值；
        int streamVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setProgress(streamVolume);
        tv.setText(streamVolume + "");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    //设置声音大小
                    //是否显示UI，是否需要声音
                    manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
                    tv.setText(progress + "");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //检测按键；

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            sb.setProgress(sb.getProgress() - 1);
            tv.setText(sb.getProgress() + "");
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            sb.setProgress(sb.getProgress() + 1);
            tv.setText(sb.getProgress() + "");
        }
        return super.onKeyDown(keyCode, event);
    }
}
