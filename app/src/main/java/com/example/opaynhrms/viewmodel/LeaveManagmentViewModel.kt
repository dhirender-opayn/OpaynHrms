package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.LeaveDetailCartAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*


class LeaveManagmentViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityLeaveManagementBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    val bundle = Bundle()

    fun setBinder(binder: ActivityLeaveManagementBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        setclicks()
        setAdapter()
        settoolbar()


    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = mContext.getString(R.string.leavemanagment)
    }

    private fun setAdapter() {
        val totalLeaveStatusAdapter = TotalLeaveStatusAdapter(baseActivity) {

        }
        binder.rvTotalleavestatus.adapter = totalLeaveStatusAdapter

        val leaveDetailCartAdapter = LeaveDetailCartAdapter(baseActivity) {

        }
        binder.rvLeaveDatailcart.adapter = leaveDetailCartAdapter
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }




}