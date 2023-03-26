package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class login : AppCompatActivity() {

    companion object {
        var user:String? =null
        var tx:String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val name: EditText? = findViewById(R.id.username_edit)

        button.setOnClickListener {
         user = name?.text.toString().trim()
            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        imageButton2.setOnClickListener {
            val intent = Intent(this, choose::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        if(tx=="t1")
        {
            imageButton2.setImageResource(R.drawable.t1)
        }

        if(tx=="t2")
        {
            imageButton2.setImageResource(R.drawable.t2)
        }

        if(tx=="t3")
        {
            imageButton2.setImageResource(R.drawable.t3)
        }

        if(tx=="t4")
        {
            imageButton2.setImageResource(R.drawable.t4)
        }

        if(tx=="t5")
        {
            imageButton2.setImageResource(R.drawable.t5)
        }

        if(tx=="t6")
        {
            imageButton2.setImageResource(R.drawable.t6)
        }

        if(tx=="t7")
        {
            imageButton2.setImageResource(R.drawable.t7)
        }
        if(tx=="t8")
        {
            imageButton2.setImageResource(R.drawable.t8)
        }
        if(tx=="t9")
        {
            imageButton2.setImageResource(R.drawable.t9)
        }
        if(tx=="t10")
        {
            imageButton2.setImageResource(R.drawable.t10)
        }
        if(tx=="t11")
        {
            imageButton2.setImageResource(R.drawable.t11)
        }
        if(tx=="t12")
        {
            imageButton2.setImageResource(R.drawable.t12)
        }
        if(tx=="t13")
        {
            imageButton2.setImageResource(R.drawable.t13)
        }
        if(tx=="t14")
        {
            imageButton2.setImageResource(R.drawable.t14)
        }
        if(tx=="t15")
        {
            imageButton2.setImageResource(R.drawable.t15)
        }
    }
}