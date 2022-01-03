package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.NotificationAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class Notification : KotlinBaseActivity() {

    lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)
        setadapter()
        settoolbar()
        setclick()

    }


    private fun setadapter() {
        binding.rvNotification.adapter = NotificationAdapter(baseActivity = this) {

        }
    }

    private fun settoolbar() {
        binding.toolbar.tvtitle.text = getString(R.string.notification)

    }

    private fun setclick() {
        binding.toolbar.icmenu.setOnClickListener {
            onBackPressed()
        }
    }
}