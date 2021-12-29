package com.example.opaynhrms.ui


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.viewmodel.ChangePasswordViewModel

class ChangePassword : KotlinBaseActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    lateinit var viewmodel: ChangePasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        viewmodel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }
}