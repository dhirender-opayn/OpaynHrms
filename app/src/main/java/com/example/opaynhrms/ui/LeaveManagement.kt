package com.example.opaynhrms.ui
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.viewmodel.LeaveManagmentViewModel

class LeaveManagement : KotlinBaseActivity()
{
    lateinit var binding: ActivityLeaveManagementBinding
    lateinit var viewmodel: LeaveManagmentViewModel
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leave_management)
        viewmodel = ViewModelProvider(this).get(LeaveManagmentViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }

    override fun onResume() {
        super.onResume()
        viewmodel.leavelist()
    }
}