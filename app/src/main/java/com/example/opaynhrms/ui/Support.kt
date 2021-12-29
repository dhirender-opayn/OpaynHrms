package com.example.opaynhrms.ui


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivitySupportBinding
import com.example.opaynhrms.viewmodel.SupportViewModel
import kotlinx.android.synthetic.main.activity_login.*


class Support : KotlinBaseActivity() {
    lateinit var binding:ActivitySupportBinding
    lateinit var viewmode:SupportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_support)
        viewmode = ViewModelProvider(this).get(SupportViewModel::class.java)
        viewmode.setBinder(binding,this)
    }
}