package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import com.example.opaynhrms.base.AppViewModel

class ChangePasswordViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityChangePasswordBinding
    var loginSigupRepository: LoginRepository = LoginRepository(application)
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityChangePasswordBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()

    }
    private  fun setclicks()
    {
        binder.loginbtn.setOnClickListener {
            if (validations())
            {
                changepasssworsApi()
            }
        }
    }

    private  fun validations():Boolean
    {
        if (binder.tvoldpassword.text.toString().isEmpty())
        {
            showToast(mContext.getString(R.string.v_currentpassword))
            return false
        }
        if (binder.tvnewpassord.text.toString().trim().isEmpty())
        {
            showToast(mContext.getString(R.string.v_newpassword))
            return false
        }
        if (binder.tvnewpassord.text.toString().trim().length<6)
        {
            showToast(mContext.getString(R.string.passwordlength))
            return false
        }
        if (binder.tvreEnter.text.toString().trim().isEmpty())
        {
            showToast(mContext.getString(R.string.v_enwconfirmpassword))
            return false
        }
        if (binder.tvreEnter.text.toString().trim().length<6)
        {
            showToast(mContext.getString(R.string.passwordlength))
            return false
        }
        if (!binder.tvreEnter.text.toString().trim().equals(binder.tvnewpassord.text.toString().trim()))
        {
            showToast(mContext.getString(R.string.passwormatcherror))
            return false
        }

        return true
    }

    private  fun changepasssworsApi()
    {
        val jsonobj= JsonObject()
        jsonobj.addProperty(Keys.old_password,binder.tvoldpassword.text.toString())
        jsonobj.addProperty(Keys.new_password,binder.tvnewpassord.text.toString())
        loginSigupRepository.commonpostwithtoken(baseActivity, Keys.CHANGEPASSWORD,jsonobj){
            baseActivity.logout()

         }
    }




}