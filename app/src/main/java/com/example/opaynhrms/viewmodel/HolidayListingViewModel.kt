package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.ClockifyTaskListingAdapter
import com.example.opaynhrms.adapter.HolidayListingAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHolidayListingBinding
import com.example.opaynhrms.databinding.ActivityHomeBindingImpl
import com.example.opaynhrms.databinding.FragementClockifyworkListingBinding
import com.example.opaynhrms.databinding.FragmentAddticketBinding
import kotlinx.android.synthetic.main.common_toolbar.view.*

class HolidayListingViewModel(application: Application) : AppViewModel(application),View.OnClickListener
{
    private lateinit var binder: ActivityHolidayListingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityHolidayListingBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setclick()
        binder.rvHolidaylist.adapter = HolidayListingAdapter(baseActivity){

        }

    }



    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.light_gre1))
        binder.toolbar.tvtitle.text = mContext.getString(R.string.holidaylisting)
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
    }

    private fun setclick() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }

        }
    }
}