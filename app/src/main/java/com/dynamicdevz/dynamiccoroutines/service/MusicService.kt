package com.dynamicdevz.dynamiccoroutines.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.dynamicdevz.dynamiccoroutines.R


class MusicService: Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG_X", "Service created")

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.distant_waves)
        mediaPlayer.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG_X", "onStartCommand")

        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG_X", "Service destroyed")
    }
    override fun onBind(p0: Intent?): IBinder {
        //3. create instance of MusicBinder(inner class)
        Log.d("TAG_X", "onBind.. :)")
        return MusicBinder()
    }

    fun pause() {
        if(mediaPlayer.isPlaying)
            mediaPlayer.pause()
        else
            mediaPlayer.start()
    }

    //Concerting to bound service
    //1. create inner class that extends Binder
    inner class MusicBinder: Binder(){
        //2. create a method that returns an instance of the MusicService class
        fun getInstance(): MusicService = this@MusicService
    }

}











