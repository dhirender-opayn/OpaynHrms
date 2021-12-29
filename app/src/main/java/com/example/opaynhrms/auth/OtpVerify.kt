package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityOtpVerifyBinding
import com.example.opaynhrms.extensions.hideKeyboard

class OtpVerify : KotlinBaseActivity() {
    lateinit var binding:ActivityOtpVerifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verify)
         click()
    }

    private fun click(){

        binding.otpcontainer.setOnClickListener {
            hideKeyboard()
        }

       binding.loginbtn.setOnClickListener {
            openA(Login::class)
        }
    }
}