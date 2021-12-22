package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.opaynhrms.R
import com.example.opaynhrms.auth.ForgotPassword
import com.example.opaynhrms.auth.Signup
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLoginBinding
import com.example.opaynhrms.ui.Home
import com.ieltslearning.base.AppViewModel
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(application: Application) : AppViewModel(application), ItemClick {
    var msg: String = ""

    private lateinit var binder: ActivityLoginBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivityLoginBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        click()
        genderselction()
    }


    private fun click() {
        binder.createaccount.setOnClickListener {
            baseActivity.openA(Signup::class)
        }
        baseActivity.forgotpassword.setOnClickListener {
            baseActivity.openA(ForgotPassword::class)
        }
        baseActivity.loginbtn.setOnClickListener {
            baseActivity.openA(Home::class)
        }
    }

    private fun genderselction() {
        baseActivity.customSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                baseActivity.customSwitch.setThumbResource(R.drawable.ic_male_symbol)
                msg = "Switch Button is Checked"
            } else {
                baseActivity.customSwitch.setThumbResource(R.drawable.ic_baseline_female_24)
                msg = "Switch Button is UnChecked"
            }



            Log.e("ddedeeeeeeee", msg.toString())

        }
    }

    override fun onItemViewClicked(position: Int, type: String) {

    }
}