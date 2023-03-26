package com.example.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_new_play.*

class NewPlay : AppCompatActivity() {

    val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            val playBinder:MyAudioService3.PlayBinder = iBinder as MyAudioService3.PlayBinder
            //获取代理人对象
            playBinder.MyMethod();  //调用代理方法
        }

        override fun onServiceDisconnected( componentName: ComponentName) {
            //断开服务连接
        }
    }

    var answer1: Boolean? =false
    var answer2: Boolean? =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_play)

        initiSoundPool()

        btnyazi.setOnClickListener {
            xuan3.setImageResource(R.drawable.gou)
            answer1=true
        }

        btnezi.setOnClickListener {
            xuan3.setImageResource(R.drawable.baozi)
            answer1=false
        }

        btnzhuzi.setOnClickListener {
            xuan1.setImageResource(R.drawable.ying)
            answer2=true
        }

        btnsongshu.setOnClickListener {
            xuan1.setImageResource(R.drawable.niao)
            answer2=false
        }

        exitmap.setOnClickListener {
            finish()
        }

        confirm_button.setOnClickListener {
            if (answer1 == true && answer2 == true)
            {
                val intent = Intent(this, Win3Activity::class.java)
                playSound(1,0)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
            else{
                val intent = Intent(this, wrongpage::class.java)
                playSound(2,0)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
        }
        exitmap.setOnClickListener {
            finish()
        }
    }
    public fun playSound(sound: Int, number: Int) { //sound:播放音效的id，number:放音效的次数

        val am: AudioManager = this.getSystemService(AUDIO_SERVICE) as AudioManager//实例化AudioManager对象

        var audioMaxVolumn: Int =
            am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //返回当前AudioManager对象的最大音量值

        var audioCurrentVolumn: Int =
            am.getStreamVolume(AudioManager.STREAM_MUSIC);//返回当前AudioManager对象的音量值

        val volumnRatio: Int = 10



        spMap?.get(sound)?.let {
            sp?.play(

                it,      //播放的音乐id

                volumnRatio.toFloat(),       //左声道音量

                volumnRatio.toFloat(),       //右声道音量

                2,          //优先级，0为最低

                number,        //循环次数，0无不循环，-1无永远循环

                1F         //回放速度 ，该值在0.5-2.0之间，1为正常速度

            )
        }

    }

    fun initiSoundPool() {
        sp = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        spMap = HashMap()
        spMap!![1] = sp!!.load(this, R.raw.juanzhoumsc, 2)
        spMap!![2] = sp!!.load(this, R.raw.wrongmsc, 1)

    }

    var sp: SoundPool? = null
    var spMap: HashMap<Int, Int>? = null

    override fun onResume() {
        super.onResume()
        val intent2 = Intent(this,MyAudioService3::class.java)
        bindService(intent2,connection,BIND_AUTO_CREATE);   //绑定服务
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}