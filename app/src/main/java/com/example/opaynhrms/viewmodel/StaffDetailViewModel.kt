package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.databinding.ActivityStaffListingBinding
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class StaffDetailViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityStaffDetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityStaffDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Staff Detail"
    }


    private fun setAdapter() {

    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }

}