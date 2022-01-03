package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.EmergenecyAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEmergencyLeaveListBinding
import com.example.opaynhrms.base.AppViewModel
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*

class EmergencyListViewModel(application: Application) : AppViewModel(application), ItemClick{


    private lateinit var binder: ActivityEmergencyLeaveListBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivityEmergencyLeaveListBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity

        setAdapter()
        settoolbar()
        setclicks()
    }




    private fun settoolbar(){
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity ,R.color.black))
        binder.toolbar.tvtitle.text = baseActivity.getString( R.string.emergencyleave)
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
    }

    private fun setAdapter() {
        val emergencyAdaperView = EmergenecyAdapter(baseActivity) {

        }
        binder.rvLeaveDatailcart.adapter = emergencyAdaperView
    }


    override fun onItemViewClicked(position: Int, type: String) {

    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }


}