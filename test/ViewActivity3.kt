package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view2.*
import kotlinx.android.synthetic.main.activity_view3.*

class ViewActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view3)

        btnback3.setOnClickListener {
            finish()
        }
    }
}