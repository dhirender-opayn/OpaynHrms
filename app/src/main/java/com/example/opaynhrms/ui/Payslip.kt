package com.example.opaynhrms.ui


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.databinding.ActivityPayslipBinding
import com.example.opaynhrms.viewmodel.ChangePasswordViewModel
import com.example.opaynhrms.viewmodel.PayslipViewModel

class Payslip : KotlinBaseActivity() {

    lateinit var binding: ActivityPayslipBinding
    lateinit var viewmodel: PayslipViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_payslip)
        viewmodel = ViewModelProvider(this).get(PayslipViewModel::class.java)
        viewmodel.setBinder(binding, this)
    }
}