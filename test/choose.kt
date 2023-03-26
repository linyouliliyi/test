package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose.*

class choose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        imageButton3.setOnClickListener {
            login.tx="t1"
            finish()
        }
        imageButton4.setOnClickListener {
            login.tx="t2"
            finish()
        }
        imageButton5.setOnClickListener {
            login.tx="t3"
            finish()
        }
        imageButton7.setOnClickListener {
            login.tx="t4"
            finish()
        }
        imageButton8.setOnClickListener {
            login.tx="t5"
            finish()
        }
        imageButton9.setOnClickListener {
            login.tx="t6"
            finish()
        }
        imageButton10.setOnClickListener {
            login.tx="t7"
            finish()
        }
        imageButton11.setOnClickListener {
            login.tx="t8"
            finish()
        }
        imageButton12.setOnClickListener {
            login.tx="t9"
            finish()
        }
        imageButton13.setOnClickListener {
            login.tx="t10"
            finish()
        }
        imageButton14.setOnClickListener {
            login.tx="t11"
            finish()
        }
        imageButton15.setOnClickListener {
            login.tx="t12"
            finish()
        }
        imageButton16.setOnClickListener {
            login.tx="t13"
            finish()
        }
        imageButton17.setOnClickListener {
            login.tx="t14"
            finish()
        }
        imageButton18.setOnClickListener {
            login.tx="t15"
            finish()
        }
    }


}