package com.example.opaynhrms.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAttendenceListBinding
import com.example.opaynhrms.viewmodel.AttendenceListViewModel

class AttendanceList : KotlinBaseActivity() {

    lateinit var binding: ActivityAttendenceListBinding
    lateinit var viewmodel: AttendenceListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendence_list)
        viewmodel = ViewModelProvider(this).get(AttendenceListViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }
}