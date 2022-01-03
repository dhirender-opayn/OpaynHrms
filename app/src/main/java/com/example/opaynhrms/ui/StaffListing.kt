package com.example.opaynhrms.ui
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffListingBinding
import com.example.opaynhrms.viewmodel.StaffListingViewModel
class StaffListing : KotlinBaseActivity()
{
    lateinit var binding: ActivityStaffListingBinding
    lateinit var viewmodel: StaffListingViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff_listing)
        viewmodel = ViewModelProvider(this).get(StaffListingViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }

    override fun onResume() {
        super.onResume()
        viewmodel.callUserApi()
    }
}