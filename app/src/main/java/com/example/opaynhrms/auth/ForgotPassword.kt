package com.example.opaynhrms.auth

 import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityForgotPasswordBinding

class ForgotPassword : KotlinBaseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        click()
    }

    private fun click() {
        binding.loginbtn.setOnClickListener {
            openA(OtpVerify::class)
            finishAffinity()
        }
    }
}