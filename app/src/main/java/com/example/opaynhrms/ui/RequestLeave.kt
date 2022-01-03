package com.example.opaynhrms.ui


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
 import com.example.opaynhrms.viewmodel.RequestLeaveViewModel

class RequestLeave : KotlinBaseActivity()
{

    lateinit var binding: ActivityRequestLeaveBinding
    lateinit var viewmodel: RequestLeaveViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_leave)
        viewmodel = ViewModelProvider(this).get(RequestLeaveViewModel::class.java)
        viewmodel.setBinder(binding, this)

    }

}