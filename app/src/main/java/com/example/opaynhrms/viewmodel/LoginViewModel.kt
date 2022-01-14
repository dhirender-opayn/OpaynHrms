package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.auth.ForgotPassword
import com.example.opaynhrms.auth.Signup
import com.example.opaynhrms.base.KotlinBaseActivity
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
import com.example.opaynhrms.base.AppViewModel
import com.ieltslearning.listner.ItemClick

class LoginViewModel(application: Application) : AppViewModel(application), ItemClick,
    View.OnClickListener {
    var msg: String = ""
    var loginSigupRepository: LoginRepository = LoginRepository(application)
    private lateinit var binder: ActivityLoginBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivityLoginBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        click()
    }


    private fun click() {
        binder.createaccount.setOnClickListener(this)
        binder.forgotpassword.setOnClickListener(this)
     //   binder.loginbtn.setOnClickListener(this)

    }

    private fun viewvalidations(): Boolean {
        if (binder.tvEmail.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_email))
            return false
        }
        if (!isEmailValid(binder.tvEmail.text!!.trim().toString())) {
            showToast(mContext.getString(R.string.v_validemail))
            return false
        }
        if (binder.tvpassword.text.toString().isEmpty()) {
            showToast(mContext.getString(R.string.v_email))
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

    override fun onItemViewClicked(position: Int, type: String) {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.createaccount -> {
                baseActivity.openA(Signup::class)
            }
            R.id.forgotpassword -> {
                baseActivity.openA(ForgotPassword::class)
            }
            R.id.loginbtn -> {


            }

        }
    }

    private fun loginApi() {
        val jsonobj = JsonObject()
        jsonobj.addProperty(Keys.email, binder.tvEmail.text.toString())
        jsonobj.addProperty(Keys.password, binder.tvpassword.text.toString())
        jsonobj.addProperty(Keys.rememberMe, "true")
        loginSigupRepository.getlogin(baseActivity, "", jsonobj) {
            if (it.data.isNotNull()) {
                val gson = Gson()
                val json = gson.toJson(it)
                baseActivity.preferencemanger.saveString(USERDATA, json)
                baseActivity.preferencemanger.saveString(TOKEN, it.data.token)
                baseActivity.openA(Home::class)
                baseActivity.finishAffinity()
            }
        }
    }
}