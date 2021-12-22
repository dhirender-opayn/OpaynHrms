package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.viewmodel.HomeViewModel

class Home : KotlinBaseActivity(R.id.container) {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewmodel.setBinder(binding, this)

    }
}