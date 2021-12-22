package com.example.opaynhrms.auth

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.viewmodel.LoginViewModel

class Login : KotlinBaseActivity() {
    lateinit var binding:ActivityLoginBinding
    lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setBinder(binding,this)
    }


}