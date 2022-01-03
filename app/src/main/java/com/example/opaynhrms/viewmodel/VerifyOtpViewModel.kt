package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityOtpVerifyBinding
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import com.example.opaynhrms.base.AppViewModel

class VerifyOtpViewModel(application: Application) : AppViewModel(application),View.OnClickListener{
    var msg: String = ""
    var loginSigupRepository: LoginRepository = LoginRepository(application)
    private lateinit var binder: ActivityOtpVerifyBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivityOtpVerifyBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity

        click()
    }


    private fun click() {
       binder.loginbtn.setOnClickListener(this)

    }
    private  fun viewvalidations():Boolean
    {
        if(binder.tvotpEmail.text.toString().isEmpty())
        {
            baseActivity.showtoast(baseActivity.getString(R.string.v_otp))
            return false
        }
        if(binder.tvotpEmail.text.toString().length<4)
        {
            baseActivity.showtoast(baseActivity.getString(R.string.v_otp_length))
            return false
        }
        if (binder.tvCreatePass.text.toString().trim().isEmpty())
        {
            showToast(mContext.getString(R.string.v_newpassword))
            return false
        }
        if (binder.tvCreatePass.text.toString().trim().length<6)
        {
            showToast(mContext.getString(R.string.passwordlength))
            return false
        }
        if (binder.tvReEnterPass.text.toString().trim().isEmpty())
        {
            showToast(mContext.getString(R.string.v_enwconfirmpassword))
            return false
        }
        if (binder.tvReEnterPass.text.toString().trim().length<6)
        {
            showToast(mContext.getString(R.string.passwordlength))
            return false
        }
        if (!binder.tvReEnterPass.text.toString().trim().equals(binder.tvCreatePass.text.toString().trim()))
        {
            showToast(mContext.getString(R.string.passwormatcherror))
            return false
        }

        return true
    }



    override fun onClick(p0: View?) {
         when(p0?.id)
         {
             R.id.loginbtn->{
                 if (viewvalidations())
                 {
                     verifyapi()
                 }

             }

         }
    }
    private  fun verifyapi()
    {
        val jsonobj= JsonObject()
        jsonobj.addProperty(Keys.code,binder.tvotpEmail.text.toString().trim())
        jsonobj.addProperty(Keys.password,binder.tvCreatePass.text.toString().trim())
        jsonobj.addProperty(Keys.confirm_password,binder.tvReEnterPass.text.toString().trim())
        loginSigupRepository.commonpost(baseActivity,Keys.RESETPASSWORD,jsonobj){
           baseActivity.showtoast(baseActivity.getString(R.string.otpverified_sucess))
            baseActivity.onBackPressed()
        }
    }
}