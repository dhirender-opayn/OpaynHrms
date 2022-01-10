package com.example.opaynhrms.fragment


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity

import com.example.opaynhrms.databinding.FragmentSupportTicketReportingBinding

import com.example.opaynhrms.viewmodel.SupportTicketReportingViewModel
import com.ieltslearning.base.KotlinBaseFragment

class SupportTicketReporting(val baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: FragmentSupportTicketReportingBinding
    lateinit var viewmodel: SupportTicketReportingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_support_ticket_reporting, container, false)
//        binding = DataBindingUtil.setContentView(baseActivity, R.layout.fragment_add_holiday)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(SupportTicketReportingViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)




    }







}