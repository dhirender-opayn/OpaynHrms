package com.example.opaynhrms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import com.example.opaynhrms.viewmodel.NotificationViewModel


class Notification(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: ActivityNotificationBinding
    lateinit var viewModel: NotificationViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.activity_notification, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        viewModel.setBinder(binding, baseActivity)

    }
}