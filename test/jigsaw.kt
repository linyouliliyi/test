package com.example.test

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_jigsaw.*


class jisaw : AppCompatActivity() {

    val connection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            val playBinder: MyAudioService3.PlayBinder = iBinder as MyAudioService3.PlayBinder
            //获取代理人对象
            playBinder.MyMethod();  //调用代理方法
        }

        override fun onServiceDisconnected( componentName: ComponentName) {
            //断开服务连接
        }
    }

    //  定义九个图片按钮，命名方法也是00,01这样的横纵坐标
    var ib00: ImageButton? = null
    var ib01: ImageButton? = null
    var ib02: ImageButton? = null
    var ib10: ImageButton? = null
    var ib11: ImageButton? = null
    var ib12: ImageButton? = null
    var ib20: ImageButton? = null
    var ib21: ImageButton? = null
    var ib22: ImageButton? = null

    private fun initView() {
        ib00 = findViewById(R.id.p1 )
        ib01 = findViewById(R.id.p2 )
        ib02 = findViewById(R.id.p3)
        ib10 = findViewById(R.id.p4)
        ib11 = findViewById(R.id.p5)
        ib12 = findViewById(R.id.p6)
        ib20 = findViewById(R.id.p7)
        ib21 = findViewById(R.id.p8)
        ib22 = findViewById(R.id.p9)

        restartBtn = findViewById(R.id.pt_btn_restart )
    }

    //   一个重启按钮
    private var restartBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // 设置要显示的视图
        setContentView(R.layout.activity_jigsaw)
        initView()
        initiSoundPool()
        disruptRandom()
        ditu.setOnClickListener {
            finish()
        }

        BtnOver.setOnClickListener{
            val intent= Intent(this, Win1Activity ::class.java)
            startActivity(intent)
        }

        tipbtn.setOnClickListener {
            val intent = Intent(this, tip::class.java)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            playSound(1,0)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

        }
    }
    private val image = intArrayOf(R.drawable.aab,R.drawable.aac,R.drawable.aad,R.drawable.aae,R.drawable.aaf,R.drawable.aag,R.drawable.aah,R.drawable.aaa,R.drawable.aai)
    private val imageIndex = IntArray(image.size){it+1}

    public fun onClick(view: View?) {
        val id: Int? = view?.getId()
//        九个按钮执行的点击事件的逻辑应该是相同的，如果有空格在周围，可以改变图片显示的位置，否则点击事件不响应
        if(id==R.id.p1)
            move(R.id.p1,0)
        if(id==R.id.p2)
            move(R.id.p2,1)
        if(id==R.id.p3)
            move(R.id.p3,2)
        if(id==R.id.p4)
            move(R.id.p4,3)
        if(id==R.id.p5)
            move(R.id.p5,4)
        if(id==R.id.p6)
            move(R.id.p6,5)
        if(id==R.id.p7)
            move(R.id.p7,6)
        if(id==R.id.p8)
            move(R.id.p8,7)
        if(id==R.id.p9)
            move(R.id.p9,8)
    }


    /* 重新开始按钮的点击事件*/
    public fun restart(view: View?) {
        restore();
//       将拼图重新打乱
        disruptRandom();
    }

    private fun disruptRandom() {
        imageIndex[0]=0
        imageIndex[1]=1
        imageIndex[2]=2
        imageIndex[3]=3
        imageIndex[4]=8
        imageIndex[5]=7
        imageIndex[6]=6
        imageIndex[7]=4
        imageIndex[8]=5
//        随机排列到指定的控件上
//        ib00是绑定的第一块图片按钮，设置图片资源，imageIndex[i]就是被打乱的图片数组下标，然后image[x]就表示对应下标为x的图片的id
        ib00?.setImageResource(image[imageIndex[0]]);
        ib01?.setImageResource(image[imageIndex[1]]);
        ib02?.setImageResource(image[imageIndex[2]]);
        ib10?.setImageResource(image[imageIndex[3]]);
        ib11?.setImageResource(image[imageIndex[4]]);
        ib12?.setImageResource(image[imageIndex[5]]);
        ib20?.setImageResource(image[imageIndex[6]]);
        ib21?.setImageResource(image[imageIndex[7]]);
        ib22?.setImageResource(image[imageIndex[8]]);

    }

    private fun swap(rand1:Int, rand2:Int) {
        var temp:Int = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    private var imageX:Int = 3;
    //     每列的图片个数
    private var imageY:Int = 3;

    //    图片的总数目
    private var imgCount:Int = imageX*imageY;
    //    空白区域的位置
    private var blankSwap:Int = imgCount-1;
    //    初始化空白区域的按钮id
    private var blankImgid:Int = R.id.p8

    private fun restore() {
        //      拼图游戏重新开始，允许移动碎片按钮
        ib00?.isClickable = true;
        ib01?.isClickable = true;
        ib02?.isClickable = true;
        ib10?.isClickable = true;
        ib11?.isClickable = true;
        ib12?.isClickable = true;
        ib20?.isClickable = true;
        ib21?.isClickable = true;
        ib22?.isClickable = true;
//        还原被点击的图片按钮变成初始化的模样
        val clickBtn:ImageButton  = findViewById(blankImgid);
        clickBtn.visibility = View.VISIBLE;
//        默认隐藏第九张图片
        val blankBtn:ImageButton  = findViewById(R.id.p9);
        blankBtn.visibility = View.INVISIBLE;
//        初始化空白区域的按钮id
        blankImgid = R.id.p8;
        blankSwap = imgCount-1;
    }



    private fun move(imagebuttonId:Int, site:Int) {
//        判断选中的图片在第几行,imageX为3，所以进行取整运算
        val sitex: Int = site / imageX
//        判断选中的图片在第几列,imageY为3，所以进行取模运算
        val sitey: Int = site % imageY
//        获取空白区域的坐标，blankx为行坐标，blanky为列坐标
        val blankx: Int = blankSwap / imageX
        val blanky: Int = blankSwap % imageY
//        可以移动的条件有两个
//        1.在同一行，列数相减，绝对值为1，可移动   2.在同一列，行数相减，绝对值为1，可以移动
        val x: Int = Math.abs(sitex - blankx);
        val y: Int = Math.abs(sitey - blanky);
        if ((x == 0 && y == 1) || (y == 0 && x == 1)) {
//            通过id，查找到这个可以移动的按钮
            val clickButton: ImageButton = findViewById(imagebuttonId);
//            将这个选中的图片设为不可见的，即显示为空白区域
            clickButton.visibility = View.INVISIBLE;
//            查找到空白区域的按钮
            var blankButton: ImageButton? = null
            blankButton = findViewById(blankImgid);
//            将空白区域的按钮设置为图片，image[imageIndex[site]就是刚刚选中的图片，因为这在上面disruptRandom()设置过
            if (blankButton != null) {
                blankButton.setImageResource(image[imageIndex[site]])
            };
//            移动之前是不可见的，移动之后将控件设置为可见
            if (blankButton != null) {
                blankButton.visibility = View.VISIBLE
            };
//            将改变角标的过程记录到存储图片位置的数组当中
            swap(site, blankSwap);
//            新的空白区域位置更新等于传入的点击按钮的位置
            blankSwap = site;
//            新的空白图片id更新等于传入的点击按钮的id
            blankImgid = imagebuttonId;
        }
        var loop: Boolean = true;   //定义标志位loop
        for (x in 0..8) {
            if (imageIndex[x] != x) {
                loop = false;
                break;
            }
        }

        if (loop) {
//            拼图成功了
//            拼图成功后，禁止玩家继续移动按钮
            ib00?.isClickable = false;
            ib01?.isClickable = false;
            ib02?.isClickable = false;
            ib10?.isClickable = false;
            ib11?.isClickable = false;
            ib12?.isClickable = false;
            ib20?.isClickable = false;
            ib21?.isClickable = false;
            ib22?.isClickable = false;
//            拼图成功后，第九块空白显示出图片，即下标为8的第九张图片
            ib22?.setImageResource(image[8]);
            ib22?.visibility = View.VISIBLE

            val intent = Intent(this, Win1Activity::class.java)
            playSound(1,0)
            startActivity(intent)
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

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





