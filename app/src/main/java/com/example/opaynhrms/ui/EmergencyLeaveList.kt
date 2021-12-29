package com.example.opaynhrms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEmergencyLeaveListBinding
import com.example.opaynhrms.viewmodel.EmergencyListViewModel

class EmergencyLeaveList : KotlinBaseActivity() {

    lateinit var binding: ActivityEmergencyLeaveListBinding
     lateinit var viewmodel:EmergencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_emergency_leave_list)
        viewmodel  = ViewModelProvider(this).get(EmergencyListViewModel::class.java)
        viewmodel.setBinder(binding,this)

    }
}