package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.DetailAttendanceListAdapter
import com.example.opaynhrms.adapter.LeavelistingAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.base.AppViewModel
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

        binder.rvtab.adapter = DetailAttendanceListAdapter(baseActivity){

        }

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

        binder.tvAttendance.setOnClickListener{

            binder.tvAttendance.setBackgroundResource(R.color.pinky_red)
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            binder.tvLeave.setBackgroundResource(R.color.white)

             binder.rvtab.adapter = DetailAttendanceListAdapter(baseActivity){

             }

        }
        binder.tvLeave.setOnClickListener {
            binder.tvAttendance.setBackgroundResource(R.color.white)
            binder.tvLeave.setBackgroundResource(R.color.pinky_red)
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            binder.rvtab.adapter = LeavelistingAdapter(baseActivity){

            }
        }

    }

}