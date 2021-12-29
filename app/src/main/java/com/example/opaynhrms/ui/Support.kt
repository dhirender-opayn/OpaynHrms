package com.example.opaynhrms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opaynhrms.R
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class Support : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        toolbar.tvtitle.text = "Notification"
    }
}