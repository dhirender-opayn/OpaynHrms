package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.example.opaynhrms.databinding.FragmentAddHolidayBinding
import com.example.opaynhrms.databinding.StatisticsNotificationBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.extensions.toast
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.TimePickerFragment
import com.example.opaynhrms.utils.Utils
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.reporting_checkboxs.view.*
import kotlinx.android.synthetic.main.statistics_notification.*
import okhttp3.MultipartBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ReportingViewModel(application: Application) : AppViewModel(application),
    OnChartValueSelectedListener,
    View.OnClickListener {
    private lateinit var binder: StatisticsNotificationBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    private var tf: Typeface? = null
    lateinit var bardataset: BarDataSet
    var leavechartdata = ArrayList<BarEntry>()


    fun setBinder(binder: StatisticsNotificationBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        roundbardAttendance()
        salarypiechart()

        settoolbar()
        leaveChartRadioButton()
        attendanceChartRadioButton()
        salaryChartRadioButton()


    }


    private fun leaveChartRadioButton() {
        var radio: RadioButton ?=null
        radio =  binder.leaveChart.cb_yearly
        binder.leaveChart.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                  radio   = baseActivity.findViewById(checkedId)

                Toast.makeText(
                    mContext, " On checked change :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()


            })
        Log.e("upperchecktool",radio.text.toString())
        when (radio.text) {
            baseActivity.getString(R.string.today) -> {

            }
            baseActivity.getString(R.string.weekly) -> {
                leavechartdata.clear()
                leavechartdata.add(BarEntry(1f, 420f))
                leavechartdata.add(BarEntry(2f, 520f))
                leavechartdata.add(BarEntry(3f, 620f))
                leavechartdata.add(BarEntry(4f, 720f))
                leavechartdata.add(BarEntry(5f, 820f))
                leavechartdata.add(BarEntry(6f, 920f))
                leavechartdata.add(BarEntry(7f, 920f))
                leavebarchart(leavechartdata)
            }
            baseActivity.getString(R.string.monthly) -> {
                Log.e("upperchecktool",radio.text.toString()+"inneryearly")
                leavechartdata.clear()
                leavechartdata.add(BarEntry(1f, 420f))
                leavechartdata.add(BarEntry(2f, 520f))
                leavechartdata.add(BarEntry(3f, 620f))
                leavechartdata.add(BarEntry(4f, 720f))
                leavechartdata.add(BarEntry(5f, 820f))
                leavechartdata.add(BarEntry(6f, 920f))
                leavechartdata.add(BarEntry(8f, 920f))
                leavechartdata.add(BarEntry(9f, 920f))
                leavechartdata.add(BarEntry(10f, 920f))
                leavechartdata.add(BarEntry(11f, 920f))
                leavechartdata.add(BarEntry(12f, 920f))
                leavebarchart(leavechartdata)

            }
            baseActivity.getString(R.string.yearly) -> {
                leavechartdata.clear()
                Toast.makeText(
                    mContext, " On hange :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
                leavechartdata.add(BarEntry(2015f, 120f))
                leavechartdata.add(BarEntry(2016f, 220f))
                leavechartdata.add(BarEntry(2017f, 320f))
                leavechartdata.add(BarEntry(2018f, 420f))
                leavechartdata.add(BarEntry(2019f, 520f))
                leavechartdata.add(BarEntry(2020f, 820f))
                leavechartdata.add(BarEntry(2021f, 50f))
                leavechartdata.add(BarEntry(2022f, 90f))
                leavebarchart(leavechartdata)
            }
        }
    }

    private fun attendanceChartRadioButton() {
        binder.attendanceChart.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = baseActivity.findViewById(checkedId)
                Toast.makeText(
                    mContext, " On checked change :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun salaryChartRadioButton() {
        binder.salaryChart.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = baseActivity.findViewById(checkedId)
                Toast.makeText(
                    mContext, " On checked change :" +
                            " ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }


    private fun leavebarchart( leavedata:ArrayList<BarEntry>) {

        bardataset = BarDataSet(leavedata, "Leave Reporting")

//        if (cb_weekly.isChecked){
//            bardataset = BarDataSet(weekly,"Leave Reporting")
//        }
//        if (cb_monthly.isChecked){
//            bardataset = BarDataSet(monthly,"Leave Reporting")
//        }
//        if (cb_monthly.isChecked){
//            bardataset = BarDataSet(yearly,"Leave Reporting")
//        }

        bardataset.setColor(baseActivity.getColor(R.color.violet_pink))

//        bardataset.setValueTextColors(Color.BLACK)
        bardataset.valueTextSize = 12f


        val bardata = BarData(bardataset)
        binder.pcLeaveChart.setFitBars(true)
        binder.pcLeaveChart.data = bardata
        binder.pcLeaveChart.description.text = "bar chart exm"
        binder.pcLeaveChart.animateY(2000)

    }

    private fun roundbardAttendance() {


        setData(12, 180f)

        binder.pcAttendancereport.setUsePercentValues(true)
        binder.pcAttendancereport.getDescription().setEnabled(false)
        binder.pcAttendancereport.setExtraOffsets(5f, 10f, 5f, 5f)

        binder.pcAttendancereport.setDragDecelerationFrictionCoef(0.95f)

        tf = Typeface.createFromAsset(baseActivity.assets, "OpenSans-Regular.ttf")

        binder.pcAttendancereport.setCenterTextTypeface(
            Typeface.createFromAsset(
                baseActivity.assets,
                "OpenSans-Light.ttf"
            )
        )
        binder.pcAttendancereport.setCenterText(generateCenterSpannableText())

        binder.pcAttendancereport.setExtraOffsets(20f, 0f, 20f, 0f)

        binder.pcAttendancereport.setDrawHoleEnabled(true)
        binder.pcAttendancereport.setHoleColor(Color.WHITE)

        binder.pcAttendancereport.setTransparentCircleColor(Color.WHITE)
        binder.pcAttendancereport.setTransparentCircleAlpha(110)

        binder.pcAttendancereport.setHoleRadius(58f)
        binder.pcAttendancereport.setTransparentCircleRadius(61f)

        binder.pcAttendancereport.setDrawCenterText(true)

        binder.pcAttendancereport.setRotationAngle(0f)
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        binder.pcAttendancereport.setRotationEnabled(true)
        binder.pcAttendancereport.setHighlightPerTapEnabled(true)

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        binder.pcAttendancereport.setOnChartValueSelectedListener(this)



        binder.pcAttendancereport.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        // chart.spin(2000, 0, 360);
        val l: Legend = binder.pcAttendancereport.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }


    private fun salarypiechart() {
        setDatasalary(3, 7f)
        binder.pcSalaryreport.setUsePercentValues(true)
        binder.pcSalaryreport.getDescription().setEnabled(false)
        binder.pcSalaryreport.setExtraOffsets(5f, 10f, 5f, 5f)

        binder.pcSalaryreport.setDragDecelerationFrictionCoef(0.95f)


        binder.pcSalaryreport.setCenterTextTypeface(baseActivity.tfLight)
        binder.pcSalaryreport.setCenterText(generateCenterSpannableText())

        binder.pcSalaryreport.setDrawHoleEnabled(true)
        binder.pcSalaryreport.setHoleColor(Color.WHITE)

        binder.pcSalaryreport.setTransparentCircleColor(Color.WHITE)
        binder.pcSalaryreport.setTransparentCircleAlpha(110)

        binder.pcSalaryreport.setHoleRadius(58f)
        binder.pcSalaryreport.setTransparentCircleRadius(61f)

        binder.pcSalaryreport.setDrawCenterText(true)

        binder.pcSalaryreport.setRotationAngle(0f)
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        binder.pcSalaryreport.setRotationEnabled(true)
        binder.pcSalaryreport.setHighlightPerTapEnabled(true)

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        binder.pcSalaryreport.setOnChartValueSelectedListener(this)


        binder.pcSalaryreport.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        // chart.spin(2000, 0, 360);
        val l = binder.pcSalaryreport.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling

        // entry label styling
        binder.pcSalaryreport.setEntryLabelColor(Color.WHITE)
        binder.pcSalaryreport.setEntryLabelTypeface(baseActivity.tfRegular)
        binder.pcSalaryreport.setEntryLabelTextSize(12f)
    }

    private fun setDatasalary(count: Int, range: Float) {

        val entries = java.util.ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    baseActivity.months[i % baseActivity.months.size],
                    baseActivity.resources.getDrawable(R.drawable.star)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = java.util.ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        data.setValueTypeface(baseActivity.tfLight)
        binder.pcSalaryreport.data = data

        // undo all highlights
        binder.pcSalaryreport.highlightValues(null)
        binder.pcSalaryreport.invalidate()
    }


    private fun setData(count: Int, range: Float) {
        val entries = java.util.ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range).toFloat() + range / 5,
                    baseActivity.parties.get(i % baseActivity.parties.size)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = java.util.ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);
        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        data.setValueTypeface(tf)
        binder.pcAttendancereport.data = data

        // undo all highlights
        binder.pcAttendancereport.highlightValues(null)
        binder.pcAttendancereport.invalidate()
    }


    private fun generateCenterSpannableText(): SpannableString? {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.5f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.65f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(baseActivity.getColor(R.color.light_gre1))
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.reporting)
    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }

        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun onNothingSelected() {

    }


}