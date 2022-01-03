package com.example.opaynhrms.common

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityCommonBinding
import com.example.opaynhrms.viewmodel.CommonActivityViewModel

class CommonActivity : KotlinBaseActivity(R.id.containeractivity) {
    lateinit var binding: ActivityCommonBinding
    lateinit var viewModel: CommonActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_common)
        viewModel = ViewModelProvider(this).get(CommonActivityViewModel::class.java)
        viewModel.setBinder(binding, this)
    }
}