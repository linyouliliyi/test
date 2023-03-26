package com.example.test

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_newmap.*
import kotlinx.android.synthetic.main.activity_sel.*

 class SelActivity : AppCompatActivity(){
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
     var answer: Int? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sel)
        initView()
        initiSoundPool()

        BtnOver.setOnClickListener {
            val intent = Intent(this, Win2Activity::class.java)

            startActivity(intent)
        }

        btnChoose.setOnClickListener {
        if (answer==1)
        {
            val intent = Intent(this, Win2Activity::class.java)
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


    }

    private fun initView() {
        val chooseGridFriendBtn: ImageButton = findViewById(R.id.BtnBefore)
        chooseGridFriendBtn.setOnClickListener {
            chooseGirlFriend()
        }
    }

     fun chooseGirlFriend() {
        val chooseGirlfriend = ChooseGirlfriend(2) // 只有2张图
        val girlFriend = chooseGirlfriend.renderChoose()
        //Toast.makeText(this, "" + girlFriend, Toast.LENGTH_SHORT).show()

        // 显示对应图片
        val girlFriendImageSource =  when (girlFriend){
            1 -> R.drawable.sel1
            2 -> R.drawable.sel2

            else -> R.drawable.exit
        }

        // 展示选中图片
        val girlFriendImageView: ImageView = findViewById(R.id.imagefirst)
        girlFriendImageView.setImageResource(girlFriendImageSource)
        answer=girlFriend


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



class ChooseGirlfriend(private val numDice: Int = 3) {
    fun renderChoose(): Int {
        return (1..numDice).random()
    }
}
