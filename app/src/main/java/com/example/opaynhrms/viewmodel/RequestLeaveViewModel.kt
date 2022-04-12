package com.example.opaynhrms.viewmodel

 import android.app.Application
import android.content.Context
 import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.LeaveCategoryJson
import com.example.opaynhrms.model.LeaveTypeJson
import com.example.opaynhrms.repository.RequestRepository
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
 import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.TimePickerFragment
import com.example.opaynhrms.utils.Utils
 import com.google.gson.JsonObject
 import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RequestLeaveViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: ActivityRequestLeaveBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var attachments: File? = null
    var userRepository: UserRepository = UserRepository(application)
    var selpos = 0
    val bundle = Bundle()
    var ltype = ""
    var applyradio = ""
    var leaveid = ""
    var categoryid = ""
    var isdateshow = true
    var requestRepository: RequestRepository = RequestRepository(application)
    val typelisting = ArrayList<String>()
    val leaveTypelist = ArrayList<LeaveTypeJson.Data>()
    val leaveCategorylist = ArrayList<LeaveCategoryJson.Data>()
    val categorylist = ArrayList<String>()
    fun setBinder(binder: ActivityRequestLeaveBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        settoolbar()
        leaveListing()




    }
    private fun leaveListing(){
        if (Home.leaveTypeListingJson?.data.isNotNull() && Home.leaveTypeListingJson?.data!!.size > 0){

            leaveTypelist.addAll(Home.leaveTypeListingJson!!.data)
            Home.leaveTypeListingJson!!.data.forEach {
                typelisting.add(it.type)

            }
            leavetypeAdapter(typelisting)
        }

        if (Home.categoryTypeListingJson?.data.isNotNull() && Home.categoryTypeListingJson?.data!!.size > 0){

            leaveCategorylist.addAll(Home.categoryTypeListingJson!!.data)
            Home.categoryTypeListingJson!!.data.forEach {
                categorylist.add(it.category)

            }
            leaveCategoryAdapter(categorylist)
        }
    }

    // for calling leave catgogry API
//    private fun leaveCategory() {
//        requestRepository.leaveCategory(baseActivity) {
//            if (it.data.isNotNull()) {
//
//                leaveCategorylist.addAll(it.data)
//                it.data.forEach {
//                    categorylist.add(it.category)
//
//                }
//                leaveCategoryAdapter(categorylist)
//
//            }
//        }
//    }




//    private fun checkpersion () {
//
//
//        val permissonList = ArrayList<String>()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            permissonList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
//
//        } else {
//            permissonList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            permissonList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        }
//        PermissionX.init(baseActivity)
//            .permissions(permissonList)
//            .onExplainRequestReason { scope, deniedList ->
//                scope.showRequestReasonDialog(
//                    deniedList,
//                    baseActivity.getString(R.string.permisionmsgfirst),
//                    baseActivity.getString(R.string.ok),
//                    baseActivity.getString(R.string.cancel)
//                )
//            }
//            .onForwardToSettings { scope, deniedList ->
//                scope.showForwardToSettingsDialog(
//                    deniedList,
//                    baseActivity.getString(R.string.manualpermission),
//                    baseActivity.getString(R.string.ok),
//                    baseActivity.getString(R.string.cancel)
//                )
//            }
//            .request { allGranted, grantedList, deniedList ->
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
//                {
//                    storageHelper.openFilePicker()
//                }
//                else{
//                    showdialog()
//                }
//
//            }
//        else
//        {
//
//            showtoast(getString(R.string.nopermissiongrant))
//        }
//            }

//    }

    //get cateogrty list not api live
    private fun dataget() {
        val jsonobj = JsonObject()
//        jsonobj.addProperty(Keys.user_id, user_id)
        userRepository.commonpostwithtoken(baseActivity, Keys.LEAVE_CATEGORY, jsonobj) {
            Log.e("dddddddddddddd", it.toString())

        }

    }

    private fun leavetypeAdapter(leavetypelist: ArrayList<String>) {

        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, leavetypelist)
        binder.leavetype.setAdapter(aa)
        binder.leavetype.setFocusable(false)
        binder.leavetype.setFocusableInTouchMode(false)
        binder.leavetype.setOnClickListener(this)
        binder.leavetype.setKeyListener(null)
        binder.leavetype.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            ltype = leavetypelist[i]
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

    private fun validations(): Boolean {

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
        if (ltype.equals(baseActivity.getString(R.string.half_day)) && applyradio.isEmpty()) {
            showToast("Please select  half day type")
            return false
        } else {
            if (ltype.equals(baseActivity.getString(R.string.singleday)) || ltype.equals(
                    baseActivity.getString(R.string.half_day)
                )
            ) {
                if (binder.date.text.toString().isEmpty()) {
                    showToast("Please select date")
                    return false

                }
            }

        }

        return true
    }

    private fun leaveCategoryAdapter( _categorylist:ArrayList<String> ) {
        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, _categorylist)
        binder.tvleaveCategory.setAdapter(aa)
        binder.tvleaveCategory.setFocusable(false)
        binder.tvleaveCategory.setFocusableInTouchMode(false)
        binder.tvleaveCategory.setOnClickListener(this)
        binder.tvleaveCategory.setKeyListener(null)
        binder.tvleaveCategory.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            categoryid = leaveCategorylist[i].id.toString()

        })
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity, R.color.black_4))
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)

        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.requestleave)
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
                applyradio = "5"
                binder.rbFirst.isChecked = true
                binder.rbsecond.isChecked = false
            }
        }

        binder.rbsecond.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                applyradio = "6"
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

    private fun showtimepicker(autoCompleteTextView: AutoCompleteTextView) {
        TimePickerFragment(baseActivity, object : TimePickerFragment.TimePickerInterface {
            override fun onTimeSelected(calendar: Calendar) {
                autoCompleteTextView.setText(SimpleDateFormat(Utils.TIMEFORMAT).format(calendar.time))


            }
        }).showPicker()
    }

    private fun callApi() {
        val fields = ArrayList<MultipartBody.Part>()
        Utils.getMultiPart(Keys.user_id, Home.userModel!!.data.user.id.toString())
            ?.let { fields.add(it) }
        Utils.getMultiPart(Keys.reason, binder.tvreason.text.toString().trim())
            ?.let { fields.add(it) }
        // for half day
        if (leaveid.toInt() == 3) {
            Utils.getMultiPart(Keys.type, applyradio)?.let { fields.add(it) }

        } else {
            Utils.getMultiPart(Keys.leave_type_id, leaveid)?.let { fields.add(it) }
        }
        Utils.getMultiPart(Keys.leave_category_id,categoryid)
            ?.let { fields.add(it) }

        when (leaveid.toInt()) {
            1 -> {
                Utils.getMultiPart(
                    Keys.start_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.date.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
                Utils.getMultiPart(
                    Keys.end_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.date.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
            }
            2 -> {
                Utils.getMultiPart(
                    Keys.start_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.startdate.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
                Utils.getMultiPart(
                    Keys.end_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.enddate.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }


            }
            3 -> {
                Utils.getMultiPart(
                    Keys.start_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.date.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
                Utils.getMultiPart(
                    Keys.end_date,
                    Utils.formateDateFromstring(
                        Utils.DATEFORMAT3,
                        Utils.DATEFORMAT2,
                        binder.date.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
            }
            4 -> {

                Utils.getMultiPart(
                    Keys.start_date, Utils.formateDateFromstring(
                        Utils.DATEFORMAT3, Utils.DATEFORMAT2, binder.date.text.toString().trim()
                    ) + " " + Utils.formateDateFromstring(
                        Utils.TIMEFORMAT,
                        Utils.TIMEFORMAT2,
                        binder.startime.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }


                Utils.getMultiPart(
                    Keys.end_date, Utils.formateDateFromstring(
                        Utils.DATEFORMAT3, Utils.DATEFORMAT2, binder.date.text.toString().trim()
                    ) + " " + Utils.formateDateFromstring(
                        Utils.TIMEFORMAT,
                        Utils.TIMEFORMAT2,
                        binder.endtim.text.toString().trim()
                    )
                )
                    ?.let { fields.add(it) }
            }

        }

        if (attachments != null) {
            Utils.getMultiPart(Keys.file, attachments!!)?.let { fields.add(it) }
        }



        Log.e("eeeeeeeeeeeeeeeeeeeeee",fields.toString())

        userRepository.addleave(baseActivity, fields) {
            if (!it.data.isNull()) {
                baseActivity.showtoast("Leave applied successfully")
                baseActivity.finish()
            }

        }

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
                    callApi()

                }
            }

        }
    }

}