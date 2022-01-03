package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivitySupportBinding
import com.example.opaynhrms.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class SupportViewModel(application: Application) : AppViewModel(application) {

    private lateinit var binder: ActivitySupportBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivitySupportBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        click()
    }

//    // TODO: 30-12-2021 support recycler view desgin adapter
//    private fun setAdapter() {
//
//        val supportAdapterView = SupportAdapter(baseActivity) {
//
//        }
//        binder.rvSupport.adapter = supportAdapterView
//
//    }


    //    private fun settoolbar() {
//        binder.toolbar.tvtitle.text = "Support"
//    }
    private fun click() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
        binder.loginbtn.setOnClickListener {
            if (viewvalidations()){
                baseActivity.showtoast("Thanks for contact Us, Team will contact you soon. Please check your mail for more details.")
            }

        }
    }


    private fun viewvalidations(): Boolean {
        if (binder.tvTitle.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.titlefield))
            return false
        }

        if (binder.tvAnnouncement.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.enter_the_message))
            return false
        }
        return true
    }


}