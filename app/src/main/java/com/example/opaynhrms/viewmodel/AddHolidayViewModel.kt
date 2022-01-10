package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.databinding.FragmentAddHolidayBinding
import com.example.opaynhrms.extensions.gone
 import com.example.opaynhrms.extensions.toast
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.utils.TimePickerFragment
import com.example.opaynhrms.utils.Utils
import kotlinx.android.synthetic.main.common_toolbar.view.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddHolidayViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: FragmentAddHolidayBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var file: File? = null

    val bundle = Bundle()
    var ltype = ""
    var applyradio = ""
    var leaveid = ""
    var isdateshow = true

    fun setBinder(binder: FragmentAddHolidayBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        settoolbar()
        leavetypeAdapter()
    }


    private fun leavetypeAdapter()
    {
        val roleslist = ArrayList<String>()
        roleslist.add(baseActivity.getString(R.string.singleday))
        roleslist.add(baseActivity.getString(R.string.multipleday))
        roleslist.add(baseActivity.getString(R.string.half_day))
        roleslist.add(baseActivity.getString(R.string.shortleave))
        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, roleslist)
        binder.leavetype.setAdapter(aa)
        binder.leavetype.setFocusable(false)
        binder.leavetype.setFocusableInTouchMode(false)
        binder.leavetype.setOnClickListener(this)
        binder.leavetype.setKeyListener(null)
        binder.leavetype.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            ltype = roleslist[i]
            var pos = i
            ++pos
            leaveid = pos.toString()
            when (ltype) {
                baseActivity.getString(R.string.singleday) -> {
                    binder.datecontainer.gone()
                    binder.dateWrap.visible()
                    binder.timecontainer.gone()
                    binder.radiogrp.gone()
                }
                baseActivity.getString(R.string.multipleday) -> {
                    binder.dateWrap.gone()
                    binder.datecontainer.visible()
                    binder.timecontainer.gone()
                    binder.radiogrp.gone()
                }
                baseActivity.getString(R.string.half_day) -> {
                    binder.datecontainer.gone()
                    binder.dateWrap.visible()
                    binder.radiogrp.visible()
                    binder.timecontainer.gone()
                }
                baseActivity.getString(R.string.shortleave) -> {
                    binder.radiogrp.gone()
                    binder.datecontainer.gone()
                    binder.dateWrap.visible()
                    binder.timecontainer.visible()
                }
            }


        })
    }

    private fun validations(): Boolean
    {
        if (binder.tvTitle.text.toString().isEmpty()) {
            showToast("Please enter title")
            return false
        }

        if (ltype.isEmpty()) {
            showToast("Please select leave type")
            return false
        }

        if (ltype.equals(baseActivity.getString(R.string.multipleday))) {
            if (binder.startdate.text.toString().isEmpty()) {
                showToast("Please select start date")
                return false

            }
            if (binder.enddate.text.toString().isEmpty()) {
                showToast("Please select start date")
                return false

            }
        }
        if (ltype.equals(baseActivity.getString(R.string.shortleave))) {
            if (binder.date.text.toString().isEmpty()) {
                showToast("Please select date")
                return false

            }
            if (binder.startime.text.toString().isEmpty()) {
                showToast("Please select start time")
                return false

            }
            if (binder.endtim.text.toString().isEmpty()) {
                showToast("Please select end time")
                return false

            }
        }
        if (binder.tvreason.text.toString().isEmpty()) {
            showToast("Please enter reason")
            return false
        }
        if (ltype.equals(baseActivity.getString(R.string.half_day)) &&applyradio.isEmpty())
        {
            showToast("Please select  half day type")
            return false
        }
        else{
            if (ltype.equals(baseActivity.getString(R.string.singleday))||ltype.equals(baseActivity.getString(R.string.half_day)))
            {
                if (binder.date.text.toString().isEmpty()) {
                    showToast("Please select date")
                    return false

                }
            }

        }

        return true
    }


    private fun settoolbar() {
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.addholiday)
    }


    private fun setclicks() {

        binder.date.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                return true
            }

        })
        binder.enddate.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                return true
            }

        })
        binder.startdate.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

                return true
            }

        })
        binder.startime.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return true
            }

        })
        binder.endtim.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return true
            }

        })
        binder.toolbar.icmenu.setOnClickListener(this)
        binder.leavetype.setOnClickListener(this)
        binder.loginbtn.setOnClickListener(this)
        binder.rbFirst.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                applyradio="5"
                binder.rbFirst.isChecked = true
                binder.rbsecond.isChecked = false
            }
        }

        binder.rbsecond.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                applyradio="6"
                binder.rbFirst.isChecked = false
                binder.rbsecond.isChecked = true
            }
        }


        binder.endwrap.setEndIconOnClickListener {

            if (binder.startdate.text.toString().trim().isEmpty()) {
                showToast("Please select start date first")
                return@setEndIconOnClickListener
            }
            if (isdateshow) {
                isdateshow = false
                Utils.shoedatepicker(baseActivity, binder.enddate, onConfirmed = {
                    isdateshow = true
                })
            }

        }
        binder.startdatewrap.setEndIconOnClickListener {


            if (isdateshow) {
                isdateshow = false
                Utils.shoedatepicker(baseActivity, binder.startdate, onConfirmed = {
                    isdateshow = true
                })
            }

        }

        binder.dateWrap.setEndIconOnClickListener {
            if (isdateshow) {
                isdateshow = false
                Utils.shoedatepicker(baseActivity, binder.date, onConfirmed = {
                    isdateshow = true
                })
            }
        }
        binder.endtimewrap.setEndIconOnClickListener {
            if (binder.startime.text.toString().trim().isEmpty()) {
                showToast("Please select start time first")
                return@setEndIconOnClickListener
            }
            showtimepicker(binder.endtim)

        }
        binder.starttimewarp.setEndIconOnClickListener {

            showtimepicker(binder.startime)
        }

    }

    private fun showtimepicker(autoCompleteTextView: AutoCompleteTextView)
    {
        TimePickerFragment(baseActivity, object : TimePickerFragment.TimePickerInterface {
            override fun onTimeSelected(calendar: Calendar) {
                autoCompleteTextView.setText(SimpleDateFormat(Utils.TIMEFORMAT).format(calendar.time))

                //todo
            }
        }).showPicker()
    }

//    private fun callApi()
//    {
//        val fields = ArrayList<MultipartBody.Part>()
//        Utils.getMultiPart(Keys.user_id, Home.userModel!!.data.user.id.toString())
//            ?.let { fields.add(it) }
//        Utils.getMultiPart(Keys.reason, binder.tvreason.text.toString().trim())?.let { fields.add(it) }
//       // for half dayy
//        if (leaveid.toInt()==3)
//        {
//            Utils.getMultiPart(Keys.type, applyradio)?.let { fields.add(it) }
//
//        }
//        else
//        {
//            Utils.getMultiPart(Keys.type, leaveid)?.let { fields.add(it) }
//        }
//
//
//        when(leaveid.toInt())
//        {
//            1->{
//                Utils.getMultiPart(Keys.start_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()))
//                    ?.let { fields.add(it) }
//                Utils.getMultiPart(Keys.end_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()))
//                    ?.let { fields.add(it) }
//            }
//            2->{
//                Utils.getMultiPart(Keys.start_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.startdate.text.toString().trim()))
//                    ?.let { fields.add(it) }
//                Utils.getMultiPart(Keys.end_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.enddate.text.toString().trim()))
//                    ?.let { fields.add(it) }
//
//
//
//            }
//            3->{
//                Utils.getMultiPart(Keys.start_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()))
//                    ?.let { fields.add(it) }
//                Utils.getMultiPart(Keys.end_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()))
//                    ?.let { fields.add(it) }
//            }
//            4->{
//
//                Utils.getMultiPart(Keys.start_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()
//                )+" "+ Utils.formateDateFromstring(Utils.TIMEFORMAT,Utils.TIMEFORMAT2,binder.startime.text.toString().trim()))
//                    ?.let { fields.add(it) }
//
//
//                Utils.getMultiPart(Keys.end_date, Utils.formateDateFromstring(Utils.DATEFORMAT3,Utils.DATEFORMAT2,binder.date.text.toString().trim()
//                )+" "+ Utils.formateDateFromstring(Utils.TIMEFORMAT,Utils.TIMEFORMAT2,binder.endtim.text.toString().trim()))
//                    ?.let { fields.add(it) }
//            }
//
//        }
//
//
//
//    }


    fun setfile(file: File) {
        this.file = file
        Glide.with(baseActivity).load(file).diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).into(binder.ivholiday)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.leavetype -> {
                baseActivity.showDropDown(binder.leavetype)
            }
            R.id.loginbtn -> {
                if (validations()) {
//                    callApi()
                    baseActivity.toast("Dummy Add Holiday Successfully")
                    baseActivity.onBackPressed()
                }
            }

        }
    }

}