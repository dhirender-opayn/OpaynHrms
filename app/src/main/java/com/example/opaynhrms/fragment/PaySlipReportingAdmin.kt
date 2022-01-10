package com.example.opaynhrms.fragment

 import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.databinding.FragmentPayslipReportingAdminBinding

import com.example.opaynhrms.viewmodel.PaySlipReportingAdminViewModel
import com.ieltslearning.base.KotlinBaseFragment


class PaySlipReportingAdmin(val baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: FragmentPayslipReportingAdminBinding
    lateinit var viewmodel: PaySlipReportingAdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payslip_reporting_admin, container, false)
//        binding = DataBindingUtil.setContentView(baseActivity, R.layout.fragment_add_holiday)
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(PaySlipReportingAdminViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)




    }

}