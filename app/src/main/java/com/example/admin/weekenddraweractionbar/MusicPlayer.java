package com.example.admin.weekenddraweractionbar;

import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MusicPlayer extends AppCompatActivity {

    int duration;
    MediaPlayer musicPlayer;
    SeekBar seekBar;
    TextView tvTime, tvTotalDuration, tvTitle;
    Handler mHandler;
    Thread mThread;
    TimeUnit tu;
    int timer, change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        seekBar = (SeekBar) findViewById(R.id.sbTrack);
        musicPlayer = MediaPlayer.create(this,R.raw.tgu);

        //get Title and Artist
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();


        duration = musicPlayer.getDuration();
        tvTime = (TextView) findViewById(R.id.tvTime);

        timer = 0;
        tu = TimeUnit.SECONDS;
        seekBar.setMax(duration);
        mHandler = new Handler();
        tvTotalDuration = (TextView) findViewById(R.id.tvTotalDuration);
        tvTotalDuration.setText(tu.toMinutes(duration/1000)+":"+duration/1000%60);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                change = i;
                setTextViewTime();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                musicPlayer.seekTo(change);
                timer = change/1000;
                setTextViewTime();
            }
        });

        mThread = new Thread() {
            @Override
            public void run() {
                timer += 1;
                seekBar.setProgress(timer*1000);
                //Log.d("TAG", "run: ");
                mHandler.postDelayed(mThread,1000);
            }
        };

    }

    private void setTextViewTime() {
        tvTime.setText(tu.toMinutes(timer)+":"+timer%60);
    }

    public void Play(View view) {
        musicPlayer.start();
        mThread.run();
    }

    public void Pause(View view) {
        musicPlayer.pause();
        mHandler.removeCallbacks(mThread);
    }

    public void Stop(View view) {
        musicPlayer.stop();
                musicPlayer = MediaPlayer.create(this,R.raw.tgu);
        mHandler.removeCallbacks(mThread);
        seekBar.setProgress(0);
        timer = 0;
        setTextViewTime();
    }

    @Override
    protected void onStop() {
        musicPlayer.stop();
        mHandler.removeCallbacks(mThread);
        super.onStop();
    }

}
