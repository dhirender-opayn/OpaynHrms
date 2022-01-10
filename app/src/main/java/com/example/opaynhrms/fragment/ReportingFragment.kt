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
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.KotlinBaseFragment
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


class ReportingFragment(var baseActivity: KotlinBaseActivity) : KotlinBaseFragment(),
    OnChartValueSelectedListener,
    View.OnClickListener {
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
        return inflater.inflate(R.layout.statistics_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roundbardAttendance()
        salarypiechart()
        barchart()
        settoolbar()



    }




    private fun barchart() {
        //        var weekly = ArrayList<BarEntry>()
//        weekly.add(BarEntry(1f,420f))
//        weekly.add(BarEntry(2f,520f))
//        weekly.add(BarEntry(3f,620f))
//        weekly.add(BarEntry(4f,720f))
//        weekly.add(BarEntry(5f,820f))
//        weekly.add(BarEntry(6f,920f))
//        weekly.add(BarEntry(7f,920f))
//
//        var monthly = ArrayList<BarEntry>()
//        monthly.add(BarEntry(1f,420f))
//        monthly.add(BarEntry(2f,520f))
//        monthly.add(BarEntry(3f,620f))
//        monthly.add(BarEntry(4f,720f))
//        monthly.add(BarEntry(5f,820f))
//        monthly.add(BarEntry(6f,920f))
//        monthly.add(BarEntry(8f,920f))
//        monthly.add(BarEntry(9f,920f))
//        monthly.add(BarEntry(10f,920f))
//        monthly.add(BarEntry(11f,920f))
//        monthly.add(BarEntry(12f,920f))

        var yearly = ArrayList<BarEntry>()
        yearly.add(BarEntry(2015f, 120f))
        yearly.add(BarEntry(2016f, 220f))
        yearly.add(BarEntry(2017f, 320f))
        yearly.add(BarEntry(2018f, 420f))
        yearly.add(BarEntry(2019f, 520f))
        yearly.add(BarEntry(2020f, 820f))
        yearly.add(BarEntry(2021f, 50f))
        yearly.add(BarEntry(2022f, 90f))



        bardataset = BarDataSet(yearly, "Leave Reporting")

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
        pc_leaveChart.setFitBars(true)
        pc_leaveChart.data = bardata
        pc_leaveChart.description.text = "bar chart exm"
        pc_leaveChart.animateY(2000)

    }

    private fun roundbardAttendance() {


        setData(12, 180f)

        pc_attendancereport.setUsePercentValues(true)
        pc_attendancereport.getDescription().setEnabled(false)
        pc_attendancereport.setExtraOffsets(5f, 10f, 5f, 5f)

        pc_attendancereport.setDragDecelerationFrictionCoef(0.95f)

        tf = Typeface.createFromAsset(baseActivity.assets, "OpenSans-Regular.ttf")

        pc_attendancereport.setCenterTextTypeface(
            Typeface.createFromAsset(
                baseActivity.assets,
                "OpenSans-Light.ttf"
            )
        )
        pc_attendancereport.setCenterText(generateCenterSpannableText())

        pc_attendancereport.setExtraOffsets(20f, 0f, 20f, 0f)

        pc_attendancereport.setDrawHoleEnabled(true)
        pc_attendancereport.setHoleColor(Color.WHITE)

        pc_attendancereport.setTransparentCircleColor(Color.WHITE)
        pc_attendancereport.setTransparentCircleAlpha(110)

        pc_attendancereport.setHoleRadius(58f)
        pc_attendancereport.setTransparentCircleRadius(61f)

        pc_attendancereport.setDrawCenterText(true)

        pc_attendancereport.setRotationAngle(0f)
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        pc_attendancereport.setRotationEnabled(true)
        pc_attendancereport.setHighlightPerTapEnabled(true)

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pc_attendancereport.setOnChartValueSelectedListener(this)



        pc_attendancereport.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        // chart.spin(2000, 0, 360);
        val l: Legend = pc_attendancereport.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }


    private fun salarypiechart() {
        setDatasalary(3, 7f)
        pc_salaryreport.setUsePercentValues(true)
        pc_salaryreport.getDescription().setEnabled(false)
        pc_salaryreport.setExtraOffsets(5f, 10f, 5f, 5f)

        pc_salaryreport.setDragDecelerationFrictionCoef(0.95f)

        pc_salaryreport.setCenterTextTypeface(tfLight)
        pc_salaryreport.setCenterText(generateCenterSpannableText())

        pc_salaryreport.setDrawHoleEnabled(true)
        pc_salaryreport.setHoleColor(Color.WHITE)

        pc_salaryreport.setTransparentCircleColor(Color.WHITE)
        pc_salaryreport.setTransparentCircleAlpha(110)

        pc_salaryreport.setHoleRadius(58f)
        pc_salaryreport.setTransparentCircleRadius(61f)

        pc_salaryreport.setDrawCenterText(true)

        pc_salaryreport.setRotationAngle(0f)
        // enable rotation of the chart by touch
        // enable rotation of the chart by touch
        pc_salaryreport.setRotationEnabled(true)
        pc_salaryreport.setHighlightPerTapEnabled(true)

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        pc_salaryreport.setOnChartValueSelectedListener(this)


        pc_salaryreport.animateY(1400, Easing.EaseInOutQuad)
        // chart.spin(2000, 0, 360);

        // chart.spin(2000, 0, 360);
        val l = pc_salaryreport.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling

        // entry label styling
        pc_salaryreport.setEntryLabelColor(Color.WHITE)
        pc_salaryreport.setEntryLabelTypeface(tfRegular)
        pc_salaryreport.setEntryLabelTextSize(12f)
    }

    private fun setDatasalary(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range + range / 5).toFloat(),
                    parties[i % parties.size],
                    resources.getDrawable(R.drawable.star)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
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
        data.setValueTypeface(tfLight)
        pc_salaryreport.data = data

        // undo all highlights
        pc_salaryreport.highlightValues(null)
        pc_salaryreport.invalidate()
    }


    private fun setData(count: Int, range: Float) {
        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until count) {
            entries.add(
                PieEntry(
                    (Math.random() * range).toFloat() + range / 5,
                    parties.get(i % parties.size)
                )
            )
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors
        val colors = ArrayList<Int>()
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
        pc_attendancereport.data = data

        // undo all highlights
        pc_attendancereport.highlightValues(null)
        pc_attendancereport.invalidate()
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
         toolbar.tvtitle.setTextColor(baseActivity.getColor(R.color.light_gre1))
         toolbar.tvtitle.text = baseActivity.getString(R.string.reporting)
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