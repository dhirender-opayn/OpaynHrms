package com.example.opaynhrms.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AttendanceListReportAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
import kotlinx.android.synthetic.main.fragment_attendace_listing_report.*


class AttendaceListingReport(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendace_listing_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val attendaceListingReportView = AttendanceListReportAdapter(baseActivity){

        }
        rvReportClockify.adapter = attendaceListingReportView

    }


}