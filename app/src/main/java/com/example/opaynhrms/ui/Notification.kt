package com.example.opaynhrms.ui

import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.NotificationAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class Notification : KotlinBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        rvNotification.adapter = NotificationAdapter(baseActivity = this){

        }
        toolbar.tvtitle.text = "Notification"

    }
}