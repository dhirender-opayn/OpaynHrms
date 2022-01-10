package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Filter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AttendanceListReportAdapter
import com.example.opaynhrms.adapter.SupportTicketReportAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.dailog.FilterDailog
import com.example.opaynhrms.databinding.FragmentAddHolidayBinding
import com.example.opaynhrms.databinding.FragmentSupportTicketReportingBinding
import com.example.opaynhrms.extensions.gone
 import com.example.opaynhrms.extensions.toast
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.listner.FilterListner
import com.example.opaynhrms.utils.TimePickerFragment
import com.example.opaynhrms.utils.Utils
import kotlinx.android.synthetic.main.common_toolbar.view.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SupportTicketReportingViewModel(application: Application) : AppViewModel(application), FilterListner,
    View.OnClickListener {
    private lateinit var binder: FragmentSupportTicketReportingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var file: File? = null

    val bundle = Bundle()


    fun setBinder(binder: FragmentSupportTicketReportingBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        clicks()
        settoolbar()
        setadapter()

    }

    private fun clicks(){
        binder.toolbar.icmenu.setOnClickListener(this)
        binder.toolbar.ivcart.setOnClickListener(this)

    }

    private fun settoolbar() {
        binder.toolbar.ivcart.visible()
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.black_3))
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.leavereporting)
        binder.toolbar.ivcart.setImageResource(R.drawable.ic_baseline_filter_alt_24)
    }
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.ivcart ->{
                val dialog= FilterDailog( baseActivity = baseActivity,this)
                dialog.show(baseActivity.supportFragmentManager, dialog.getTag())
            }

        }
    }


    private fun setadapter(){
        val supportTicketReportAdapterView = SupportTicketReportAdapter(baseActivity){

        }
        binder.rvSupportTicketReport.adapter = supportTicketReportAdapterView
    }

    override fun filterdata(type: String, startdate: String, endate: String) {

    }


}