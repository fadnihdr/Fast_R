package com.fastr.fadni.fast_r;

/**
 * Created by Fadni on 29/05/2017.
 */

import android.content.Context;
import android.media.SoundPool;


public class SoundManager {
    private SoundPool pool;
    private Context context;

    public SoundManager(Context context){
        this.context = context;
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        pool = builder.build();
    }
    public int addSound(int resourceID){
        return  pool.load(context, resourceID, 1);
    }
    public void play(int soundID){
        pool.play(soundID,1,1,1,0,1);

    }
}