package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.EmpReportingDetailAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentEmpReprotingDetailBinding
 import com.example.opaynhrms.model.ReportingListingModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class EmpReportingDetailViewModell(application: Application) : AppViewModel(application),
    View.OnClickListener {

    private lateinit var binder: FragmentEmpReprotingDetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    fun setBinder(binder: FragmentEmpReprotingDetailBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        settoolbar()
        setAdapter()
    }

    private fun setAdapter(){
        val reportingList = ArrayList<ReportingListingModel>()

        reportingList.add(ReportingListingModel(R.drawable.ic_calendar_line,mContext.getString(R.string.leave_reporting) , baseActivity.getString(R.string.leave)))
        reportingList.add(ReportingListingModel(R.drawable.ic_employee, mContext.getString(R.string.attendance_reporting), baseActivity.getString(R.string.attandance)))
        reportingList.add(ReportingListingModel(R.drawable.ic_clockify, mContext.getString(R.string.work_report), mContext.getString(R.string.clockify)))
        reportingList.add(ReportingListingModel(R.drawable.ic_support, mContext.getString(R.string.support_tickets), baseActivity.getString(R.string.request)))
        reportingList.add(ReportingListingModel(R.drawable.ic_payroll_salary, mContext.getString(R.string.salary_report), baseActivity.getString(R.string.salary)))

        val reportingAdapterView = EmpReportingDetailAdapter(baseActivity){

        }
        binder.rvReporting.adapter = reportingAdapterView
        reportingAdapterView.addNewList(reportingList)
    }

    private fun setclicks() {
         binder.toolbar.icmenu.setOnClickListener(this)
    }

    private fun settoolbar() {
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.emp_reporting)
        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.black_3))

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.icmenu ->{
                baseActivity.onBackPressed()
            }
        }
    }
}