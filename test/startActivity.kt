package com.example.test


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

public class startActivity :AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //定义全屏参数
        val flag: Int = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_start)

        object : Thread() {
            override fun run() {
                super.run()
                sleep(5000) //休眠3秒
                /**
                 *
                 * 要执行的操作
                 *
                 */
                val intent:Intent= Intent(applicationContext,login::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
        }.start()
    }
}


