package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentLeaveDetailBinding
import kotlinx.android.synthetic.main.common_toolbar.view.*


class LeaveDetailViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: FragmentLeaveDetailBinding
    private lateinit var mContext: Context

    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: FragmentLeaveDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setclick()

    }
    private fun settoolbar()
    {
        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.light_gre1))
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = mContext.getString(R.string.leavedetails)
    }

    private fun setclick(){
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