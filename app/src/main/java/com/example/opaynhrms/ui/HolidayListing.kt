package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHolidayListingBinding
import com.example.opaynhrms.viewmodel.HolidayListingViewModel

class HolidayListing : KotlinBaseActivity() {
    lateinit var binding:ActivityHolidayListingBinding
    lateinit var viewModel: HolidayListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_holiday_listing)
        viewModel = ViewModelProvider(this).get(HolidayListingViewModel::class.java)
        viewModel.setBinder(binding,this)


    }
}