package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.adapter.AttendanceListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAddUserBinding
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AddUserViewModel (application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityAddUserBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    fun setBinder(binder: ActivityAddUserBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()

    }




    private fun setAdapter() {



    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }
}