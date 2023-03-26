package com.example.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_new_map.*

class NewMapActivity : AppCompatActivity() {

    val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            val playBinder:MyAudioService2.PlayBinder = iBinder as MyAudioService2.PlayBinder
            //获取代理人对象
            playBinder.MyMethod();  //调用代理方法
        }

        override fun onServiceDisconnected( componentName: ComponentName) {
            //断开服务连接
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_map)

        initiSoundPool()

        btnopen2.setOnClickListener {
            val intent = Intent(this, SelActivity::class.java)
            playSound(1,0)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            startActivity(intent)
        }

        btnExit2.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            playSound(1,0)
            startActivity(intent)
        }
    }
    private var sp: SoundPool? = null
    private var spMap: HashMap<Int, Int>? = null

    public fun playSound(sound: Int, number: Int) { //sound:播放音效的id，number:放音效的次数

        val am: AudioManager = this.getSystemService(AUDIO_SERVICE) as AudioManager//实例化AudioManager对象

        var audioMaxVolumn: Int =
            am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //返回当前AudioManager对象的最大音量值

        var audioCurrentVolumn: Int =
            am.getStreamVolume(AudioManager.STREAM_MUSIC);//返回当前AudioManager对象的音量值

        var volumnRatio: Int = 8

        spMap?.get(sound)?.let {
            sp?.play(

                it,      //播放的音乐id

                volumnRatio.toFloat(),       //左声道音量

                volumnRatio.toFloat(),       //右声道音量

                1,          //优先级，0为最低

                number,        //循环次数，0无不循环，-1无永远循环

                1F         //回放速度 ，该值在0.5-2.0之间，1为正常速度

            )
        }

    }

    fun initiSoundPool() {
        sp = SoundPool(4, AudioManager.STREAM_MUSIC, 0)
        spMap = HashMap()
        spMap!![1] = sp!!.load(this, R.raw.rip, 1)

    }
    override fun onResume() {
        super.onResume()
        val intent2 = Intent(this,MyAudioService2::class.java)
        bindService(intent2,connection,BIND_AUTO_CREATE);   //绑定服务
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}