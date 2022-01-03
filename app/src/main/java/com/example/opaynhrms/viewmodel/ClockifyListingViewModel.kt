package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.ClockifyTaskListingAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragementClockifyworkListingBinding
import kotlinx.android.synthetic.main.common_toolbar.view.*

class ClockifyListingViewModel(application: Application) : AppViewModel(application),View.OnClickListener
{
    private lateinit var binder: FragementClockifyworkListingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: FragementClockifyworkListingBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setclick()
        setAdapter()
    }

    private fun setAdapter() {
        val clockifyAdapterView = ClockifyTaskListingAdapter(baseActivity) {

        }
        binder.rvClockify.adapter = clockifyAdapterView

    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.light_gre1))
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = mContext.getString(R.string.work_history)
    }

    private fun setclick() {
        binder.toolbar.icmenu.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }

        }
    }
}