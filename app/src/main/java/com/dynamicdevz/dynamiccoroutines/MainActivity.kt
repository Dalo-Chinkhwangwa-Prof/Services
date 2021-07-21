package com.dynamicdevz.dynamiccoroutines

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import com.dynamicdevz.dynamiccoroutines.service.MusicService
import com.dynamicdevz.dynamiccoroutines.viewmodel.JikanViewModel

class MainActivity : AppCompatActivity() {

    /*
    * 1. Started/ Normal service - must be stopped manually
    * 2. Bound Service - used for IPC - kept alive until all bound components unbind from it
    * 3. Intent Service - only service that runs on worker thread by default and self destructs when it completes the task
    * */

    private val viewModel: JikanViewModel by viewModels()

    private lateinit var musicIntent: Intent

    private lateinit var musicService: MusicService

    //4. Create a service connection object
    private val serviceConnection =  object:ServiceConnection{
        override fun onServiceConnected(p0: ComponentName, p1: IBinder?) {
            musicService = (p1 as MusicService.MusicBinder).getInstance()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            //Called when there is an error
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        musicIntent = Intent(this, MusicService::class.java)
        //startService(musicIntent)

        bindService(musicIntent, serviceConnection, Service.BIND_AUTO_CREATE)

        findViewById<Button>(R.id.pause_button).setOnClickListener {
            musicService.pause()
        }

        viewModel.responseData.observe(this, {
            it.forEach {
                Log.d("TAG_X", "Item : ${it.title}")
            }
        })

        //viewModel.getSearchResults("I am a spider")

    }


    override fun onStop() {
        super.onStop()
        stopService(musicIntent)
    }
}