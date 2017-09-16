package com.fastr.fadni.fast_r;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Main extends AppCompatActivity{

    ImageButton startButton;
    ImageButton settingsButton;
    ImageButton highscoreButton;
    MediaPlayer audio;
    SharedPreferences preferences;
    String audioPrefs;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        preferences.edit().clear().apply();
        audio = MediaPlayer.create(this, R.raw.bg);
        audioPrefs = preferences.getString("audio","on");
        if (audioPrefs.equals("on")){
            audio.start();
        }
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				finish();
            }
        });




    }

    @Override
    protected void onStart(){
        super.onStart();
        audio.start();
        audioPrefs = preferences.getString("audio","on");
        if (audioPrefs.equals("on"))
        {
            audio.start();
        }
        else {
            audio.stop();}
    }


    protected void onClick(View view){
        startButton = (ImageButton) findViewById(R.id.startbutton);
        settingsButton = (ImageButton) findViewById(R.id.settingsbutton);
        highscoreButton = (ImageButton) findViewById(R.id.highscorebutton);
        if (view == startButton){
            Intent intent = new Intent(this,Game.class);

            startActivity(intent);
        }
        if (view == settingsButton){
            Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
        }
        if (view == highscoreButton){
            Intent intent = new Intent(this,Highscore.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audio.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
