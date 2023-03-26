package com.example.test

import android.content.*
import android.graphics.Point
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.IBinder
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_map.view.*

class MapActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_map)

        initiSoundPool()

        btnExit.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            playSound(1,0)
            startActivity(intent)
        }
        btnopen.setOnClickListener {
            val intent = Intent(this, jisaw::class.java)
            playSound(1,0)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        //leftbtn.setOnClickListener(View.OnClickListener() {

           // fun onClick(view: View) {
              //  val par: RelativeLayout.LayoutParams = map.layoutParams as RelativeLayout.LayoutParams;
              //  if (par.leftMargin - 5 > 0) {
              //      par.leftMargin -= 5;
              //  }
              //  map.layoutParams = par;
           // }
       // })



    }


    override fun onResume() {
        super.onResume()
        super.onResume()
        textView6?.text = MainActivity.Count.toString()


        val intent2 = Intent(this,MyAudioService2::class.java)
        bindService(intent2,connection,BIND_AUTO_CREATE);   //绑定服务
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
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

}









