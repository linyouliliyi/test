package com.example.test

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MyAudioService3 : Service() {

    lateinit var mp: MediaPlayer

    inner class PlayBinder: Binder() {

        fun MyMethod() {                   //服务方法
            mp = MediaPlayer.create(applicationContext, R.raw.guanqia)
            mp.start()
        }
    }

    @Override
    public override fun onDestroy() {                //服务销毁时停止音乐播放
        mp.stop()
        mp.release()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return PlayBinder()                   //返回服务代理类

    }


}