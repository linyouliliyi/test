package com.example.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val connection = object : ServiceConnection  {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            val playBinder:MyAudioService.PlayBinder = iBinder as MyAudioService.PlayBinder
            //获取代理人对象
            playBinder.MyMethod();  //调用代理方法
        }

        override fun onServiceDisconnected( componentName:ComponentName) {
            //断开服务连接
        }
    }



    companion object {
        var Count:Int = 0
    }

    private var sp: SoundPool? = null
    private var spMap: HashMap<Int, Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiSoundPool()

        txbutton.setOnClickListener{
            val intent= Intent(this, login::class.java)
            startActivity(intent)
        }

        begingame.setOnClickListener{
            when (Count) {
                0 -> {
                    val intent= Intent(this, MapActivity::class.java)
                    playSound(1,0)
                    startActivity(intent)
                }
                1 -> {
                    val intent= Intent(this, NewMapActivity::class.java)
                    playSound(1,0)
                    startActivity(intent)
                }
                2 -> {
                    val intent= Intent(this, NewMap2Activity::class.java)
                    playSound(1,0)
                    startActivity(intent)
                }
                else ->
                {
                    val intent= Intent(this, NewMap2Activity::class.java)
                    playSound(1,0)
                    startActivity(intent)
                }
            }

        }

        juanzhoubtn.setOnClickListener{
            val intent= Intent(this, Main2Activity::class.java)
            playSound(1,0)
            startActivity(intent)
        }


    }



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
        textView6?.text = Count.toString()


        val intent2 = Intent(this,MyAudioService::class.java)
        bindService(intent2,connection,BIND_AUTO_CREATE);   //绑定服务

        if(login.tx=="t1")
        {
            txbutton.setImageResource(R.drawable.t1)
        }

        if(login.tx=="t2")
        {
            txbutton.setImageResource(R.drawable.t2)
        }

        if(login.tx=="t3")
        {
            txbutton.setImageResource(R.drawable.t3)
        }

        if(login.tx=="t4")
        {
            txbutton.setImageResource(R.drawable.t4)
        }

        if(login.tx=="t5")
        {
            txbutton.setImageResource(R.drawable.t5)
        }

        if(login.tx=="t6")
        {
            txbutton.setImageResource(R.drawable.t6)
        }

        if(login.tx=="t7")
        {
            txbutton.setImageResource(R.drawable.t7)
        }
        if(login.tx=="t8")
        {
            txbutton.setImageResource(R.drawable.t8)
        }
        if(login.tx=="t9")
        {
            txbutton.setImageResource(R.drawable.t9)
        }
        if(login.tx=="t10")
        {
            txbutton.setImageResource(R.drawable.t10)
        }
        if(login.tx=="t11")
        {
            txbutton.setImageResource(R.drawable.t11)
        }
        if(login.tx=="t12")
        {
            txbutton.setImageResource(R.drawable.t12)
        }
        if(login.tx=="t13")
        {
            txbutton.setImageResource(R.drawable.t13)
        }
        if(login.tx=="t14")
        {
            txbutton.setImageResource(R.drawable.t14)
        }
        if(login.tx=="t15")
        {
            txbutton.setImageResource(R.drawable.t15)
        }

        val username:TextView?=findViewById(R.id.username)
        username?.text = login.user
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}

