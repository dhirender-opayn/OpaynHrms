package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import com.example.opaynhrms.R
import com.example.opaynhrms.auth.Login
import com.example.opaynhrms.auth.OtpVerify
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivitySignupBinding
import com.example.opaynhrms.extensions.isEmailValid
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import com.ieltslearning.base.AppViewModel
import com.ieltslearning.listner.ItemClick

class SignupViewModel(application: Application) : AppViewModel(application) {
    var loginSigupRepository: LoginRepository = LoginRepository(application)
    private lateinit var binder: ActivitySignupBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivitySignupBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        clicks()
    }

    private fun clicks() {

        binder.singupbtn.setOnClickListener {
            if (validations()) {
                contacAdmin()
            }
        }
    }

    private fun validations(): Boolean {
        if (binder.tvName.text!!.trim().isEmpty()) {
            baseActivity.showtoast(baseActivity.getString(R.string.v_entername))
            return false
        } else if (binder.tvEmail.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_email))
            return false
        } else if (!isEmailValid(binder.tvEmail.text!!.trim().toString())) {
            showToast(mContext.getString(R.string.v_validemail))
            return false
        } else if (binder.tvMobile.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_phonenumber))
            return false
        } else if (binder.subject.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_subject))
            return false
        } else if (binder.tvdesc.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_desc))
            return false
        }
        return true
    }

    private fun contacAdmin() {
        val jsonobj = JsonObject()
        jsonobj.addProperty(Keys.email, binder.tvEmail.text.toString())
        jsonobj.addProperty(Keys.name, binder.tvName.text.toString())
        jsonobj.addProperty(Keys.subject, binder.subject.text.toString())
        jsonobj.addProperty(Keys.mobile, binder.tvMobile.text.toString())
        jsonobj.addProperty(Keys.description, binder.tvdesc.text.toString())
        jsonobj.addProperty(Keys.user_id, "")
        loginSigupRepository.commonpost(baseActivity, Keys.TICKET, jsonobj) {
            baseActivity.showtoast("Admin will contact you shortly")
        }

    }


}