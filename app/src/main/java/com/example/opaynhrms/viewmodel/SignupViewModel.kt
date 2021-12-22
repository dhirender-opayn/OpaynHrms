package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import com.example.opaynhrms.auth.Login
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivitySignupBinding
import com.ieltslearning.base.AppViewModel
import com.ieltslearning.listner.ItemClick

class SignupViewModel(application: Application) : AppViewModel(application), ItemClick {

    private lateinit var binder: ActivitySignupBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: ActivitySignupBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        click()

    }

    private fun click() {
        binder.gologin.setOnClickListener {
            baseActivity.openA(Login::class)
        }
    }


    override fun onItemViewClicked(position: Int, type: String) {

    }
}