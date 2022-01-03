package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.fragment.HomeFragement
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.viewmodel.HomeViewModel

class Home : KotlinBaseActivity(R.id.container) {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        navigateToFragment(HomeFragement(baseActivity = this),bundle,false)
        viewmodel.setBinder(binding, this)
    }


    companion object{
        var userModel:LoginJson?=null
        var rollname=""

    }
}