package com.example.opaynhrms.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import com.example.opaynhrms.databinding.ActivityPayslipBinding
import com.example.opaynhrms.viewmodel.ChangePasswordViewModel
import com.example.opaynhrms.viewmodel.NotificationViewModel
import com.example.opaynhrms.viewmodel.PayslipViewModel

class Payslip(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    lateinit var binding: ActivityPayslipBinding
    lateinit var viewmodel: PayslipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.activity_payslip, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(PayslipViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)

    }

    override fun onResume() {
        super.onResume()
        viewmodel.setdata()
    }


}