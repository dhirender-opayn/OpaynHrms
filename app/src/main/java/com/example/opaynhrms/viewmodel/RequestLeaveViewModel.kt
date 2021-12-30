package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.LeaveDetailCartAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*


class RequestLeaveViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: ActivityRequestLeaveBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    val bundle = Bundle()

    fun setBinder(binder: ActivityRequestLeaveBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        setclicks()
        setAdapter()
        settoolbar()
        testtypeadapter()


    }


    private fun getroles() {
//        userRepository.getroles(baseActivity){
//            mainrolelist.clear()
//            mainrolelist.addAll(it.data)
//            it.data.forEach {
//                roleslist.add(it.name)
//            }
//            testtypeadapter()
//        }
    }

    private fun testtypeadapter() {
        val roleslist = ArrayList<String>()
        roleslist.add("Single Day")
        roleslist.add("Multiple Day")
        roleslist.add("Half Day")
        roleslist.add("Short Leave")
        val aa = ArrayAdapter(baseActivity, R.layout.spinner_layout, roleslist)
        binder.leave.setAdapter(aa)
        binder.leave.setFocusable(false)
        binder.leave.setFocusableInTouchMode(false)
        binder.leave.setOnClickListener(this)
        binder.leave.setKeyListener(null)
//        binder.leave.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
//
//            roleid=mainrolelist[i].id.toString()
//
//        })
    }

    private fun setAdapter() {

    }


    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Request Leave"
    }


    private fun setclicks() {
//        binder.toolbar.icmenu.setOnClickListener {
//            baseActivity.onBackPressed()
//        }

        binder.toolbar.icmenu.setOnClickListener(this)
        binder.leave.setOnClickListener(this)
        binder.endwrap.setEndIconOnClickListener(this)
        binder.dateWrap.setEndIconOnClickListener(this)


        binder.endwrap.setEndIconOnClickListener {
            Log.e("dfdfdfdfdfeeeee","54545555555555555555")

        }

        binder.dateWrap.setEndIconOnClickListener {
            Log.e("dfdfdfdfdfeeeee","54545555555555555555")
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.leave -> {
                baseActivity.showDropDown(binder.leave)
            }

        }
    }

}