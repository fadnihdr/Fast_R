package com.fastr.fadni.fast_r;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {
    SharedPreferences preferences;
    Button offMusic;
    Button onMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        offMusic = (Button) findViewById(R.id.offMusic);
        onMusic = (Button) findViewById(R.id.onMusic);

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }

    public void onClick(View view){
        if (view == onMusic) {
            preferences.edit().clear().putString("audio","on").apply();
            Toast.makeText(this,"Audio ON",Toast.LENGTH_SHORT).show();
            finish();
        }
        if (view == offMusic){
            preferences.edit().clear().putString("audio","off").apply();
            Toast.makeText(this,"Audio OFF",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
