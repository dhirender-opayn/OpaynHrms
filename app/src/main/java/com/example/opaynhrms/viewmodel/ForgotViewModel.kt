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

class ForgotViewModel(application: Application) : AppViewModel(application), ItemClick,View.OnClickListener{
    var msg: String = ""
    var loginSigupRepository: LoginRepository = LoginRepository(application)
    private lateinit var binder: ActivityForgotPasswordBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivityForgotPasswordBinding, baseActivity: KotlinBaseActivity)
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
        if (binder.tvEmail.text.toString().isEmpty())
        {
            showToast(mContext.getString(R.string.v_email))
            return false
        }
        if (!isEmailValid(binder.tvEmail.text!!.trim().toString())){
            showToast(mContext.getString(R.string.v_validemail))
            return false
        }

        return true
    }

//    private fun genderselction() {
//        baseActivity.customSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                baseActivity.customSwitch.setThumbResource(R.drawable.ic_male_symbol)
//                msg = "Switch Button is Checked"
//            } else {
//                baseActivity.customSwitch.setThumbResource(R.drawable.ic_baseline_female_24)
//                msg = "Switch Button is UnChecked"
//            }
//
//
//
//            Log.e("ddedeeeeeeee", msg.toString())
//
//        }
//    }

    override fun onItemViewClicked(position: Int, type: String)
    {

    }

    override fun onClick(p0: View?) {
         when(p0?.id)
         {
             R.id.loginbtn->{
                 if (viewvalidations())
                 {
                     forgotapi()
                 }

             }

         }
    }
    private  fun forgotapi()
    {
        val jsonobj= JsonObject()
        jsonobj.addProperty(Keys.email,binder.tvEmail.text.toString())
        loginSigupRepository.commonpost(baseActivity,Keys.FORGOTPASSWORD,jsonobj){
            baseActivity.openA(OtpVerify::class)
        }
    }
}