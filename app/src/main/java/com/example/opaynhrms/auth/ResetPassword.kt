package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityResetPasswordBinding

class ResetPassword : KotlinBaseActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        click()
    }

    private fun click() {
        binding.loginbtn.setOnClickListener {
            openA(Login::class)
            finishAffinity()
        }
    }
}