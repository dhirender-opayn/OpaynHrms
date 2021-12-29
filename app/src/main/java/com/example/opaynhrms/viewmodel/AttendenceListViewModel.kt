package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AttendanceListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityAttendenceListBinding
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AttendenceListViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityAttendenceListBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    fun setBinder(binder: ActivityAttendenceListBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
    }


    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(R.color.black)
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = "Attendance List"
    }

    private fun setAdapter() {
        val attendenceListView = AttendanceListAdapter(baseActivity) {

        }
        binder.rvAttendenceList.adapter = attendenceListView


    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }
}