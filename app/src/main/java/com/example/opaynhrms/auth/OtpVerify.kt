package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityOtpVerifyBinding
import com.example.opaynhrms.viewmodel.ForgotViewModel
import com.example.opaynhrms.viewmodel.VerifyOtpViewModel

class OtpVerify : KotlinBaseActivity() {
    lateinit var binding:ActivityOtpVerifyBinding
    lateinit var viewModel: VerifyOtpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verify)
        viewModel = ViewModelProvider(this).get(VerifyOtpViewModel::class.java)
        viewModel.setBinder(binding,this)

    }


}