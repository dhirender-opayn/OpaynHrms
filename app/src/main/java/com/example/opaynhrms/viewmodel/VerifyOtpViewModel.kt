package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.auth.ForgotPassword
import com.example.opaynhrms.auth.OtpVerify
import com.example.opaynhrms.auth.Signup
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityForgotPasswordBinding
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.databinding.ActivityOtpVerifyBinding
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

class VerifyOtpViewModel(application: Application) : AppViewModel(application), ItemClick,View.OnClickListener{
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

        return true
    }



    override fun onItemViewClicked(position: Int, type: String)
    {

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
        jsonobj.addProperty(Keys.password,"7837338287k")
        jsonobj.addProperty(Keys.confirm_password,"7837338287k")
        loginSigupRepository.commonpost(baseActivity,Keys.RESETPASSWORD,jsonobj){
           baseActivity.showtoast(baseActivity.getString(R.string.otpverified_sucess))
            baseActivity.onBackPressed()
        }
    }
}