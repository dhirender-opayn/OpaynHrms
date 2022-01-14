package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.NotificationAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import com.example.opaynhrms.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class Notification : KotlinBaseActivity() {

    lateinit var binding: ActivityNotificationBinding
    lateinit var  viewModel: NotificationViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification)
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        viewModel.setBinder(binding,this)


    }


}