package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivitySignupBinding
import com.example.opaynhrms.viewmodel.SignupViewModel

class Signup : KotlinBaseActivity() {

    lateinit var binding: ActivitySignupBinding
    lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        viewModel.setBinder(binding, this)
    }


}