package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.viewmodel.StaffDetailViewModel

class StaffDetail : KotlinBaseActivity() {
    lateinit var binding: ActivityStaffDetailBinding
    lateinit var viewmodel: StaffDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff_detail)
        viewmodel = ViewModelProvider(this).get(StaffDetailViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }
    override fun onResume() {
        super.onResume()
        viewmodel.leavelist()
    }
}