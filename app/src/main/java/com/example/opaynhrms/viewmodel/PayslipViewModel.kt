package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.adapter.PaySlipListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.databinding.ActivityPayslipBinding
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class PayslipViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityPayslipBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityPayslipBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
    }


    private fun setAdapter() {

        val payslipadapterView = PaySlipListAdapter(baseActivity){

        }
        binder.rvpaysliplist.adapter = payslipadapterView
    }

    private fun settoolbar(){
        binder.toolbar.tvtitle.text = "Leave Management"
    }


    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

}