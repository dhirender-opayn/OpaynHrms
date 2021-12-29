package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.SupportAdapter
import com.example.opaynhrms.auth.ForgotPassword
import com.example.opaynhrms.auth.OtpVerify
import com.example.opaynhrms.auth.Signup
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityForgotPasswordBinding
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.databinding.ActivitySupportBinding
import com.example.opaynhrms.extensions.isEmailValid
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Keys.TOKEN
import com.example.opaynhrms.utils.Keys.USERDATA
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ieltslearning.base.AppViewModel
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_toolbar.view.*

class SupportViewModel(application: Application) : AppViewModel(application) {

    private lateinit var binder: ActivitySupportBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivitySupportBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setAdapter()
        click()
    }


    private fun setAdapter() {

        val supportAdapterView = SupportAdapter(baseActivity) {

        }
        binder.rvSupport.adapter = supportAdapterView

    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Support"
    }
    private fun click(){
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }


}