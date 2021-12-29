package com.example.opaynhrms.auth

 import android.os.Bundle
import androidx.databinding.DataBindingUtil
 import androidx.lifecycle.ViewModelProvider
 import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityForgotPasswordBinding
 import com.example.opaynhrms.viewmodel.ForgotViewModel
 import com.example.opaynhrms.viewmodel.LoginViewModel
 import com.example.opaynhrms.viewmodel.StaffDetailViewModel

class ForgotPassword : KotlinBaseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    lateinit var viewModel: ForgotViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        viewModel = ViewModelProvider(this).get(ForgotViewModel::class.java)
        viewModel.setBinder(binding,this)

    }

}