package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityOtpVerifyBinding

class OtpVerify : KotlinBaseActivity() {
    lateinit var binding:ActivityOtpVerifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verify)
         click()
    }

    private fun click(){
       binding.loginbtn.setOnClickListener {
            openA(Login::class)
            finishAffinity()
        }
    }
}