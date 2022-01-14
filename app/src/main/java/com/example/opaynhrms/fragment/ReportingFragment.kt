package com.example.opaynhrms.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
import com.example.opaynhrms.databinding.FragmentEmployeedetailBinding
import com.example.opaynhrms.databinding.StatisticsNotificationBinding
import com.example.opaynhrms.viewmodel.EmployeeViewModel
import com.example.opaynhrms.viewmodel.ReportingViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.item_statistics_team.view.*
import kotlinx.android.synthetic.main.reporting_checkboxs.*
import kotlinx.android.synthetic.main.statistics_notification.*
import java.util.ArrayList


class ReportingFragment(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(){

    lateinit var binding: StatisticsNotificationBinding
    lateinit var viewmodel: ReportingViewModel
    private var tf: Typeface? = null
    lateinit var bardataset: BarDataSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.statistics_notification, container, false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this).get(ReportingViewModel::class.java)
        viewmodel.setBinder(binding, baseActivity)



    }



}