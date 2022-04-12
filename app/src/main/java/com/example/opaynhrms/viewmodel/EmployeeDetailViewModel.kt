package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.EmpAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentEmployeedetailBinding
import com.example.opaynhrms.fragment.EmpReprotingDetail
import com.example.opaynhrms.fragment.HomeFragement
import kotlinx.android.synthetic.main.common_toolbar.view.*

import java.io.File


class EmployeeViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: FragmentEmployeedetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var file: File? = null

    val bundle = Bundle()
    var ltype = ""
    var applyradio = ""
    var leaveid = ""
    var isdateshow = true

    fun setBinder(binder: FragmentEmployeedetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        settoolbar()
        setadapter()
        click()
    }


    private fun setadapter() {
        val empAdapterView = EmpAdapter(baseActivity) {
          baseActivity.navigateToFragment(EmpReprotingDetail(baseActivity = baseActivity),bundle,true)
        }
        binder.rvEmpDetails.adapter = empAdapterView
    }


    private fun settoolbar() {
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.emp_reporting)
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity,R.color.black_3))
    }

    private fun click(){
        binder.toolbar.icmenu.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }


        }
    }

}