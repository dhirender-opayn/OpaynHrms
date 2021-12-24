package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.ieltslearning.base.AppViewModel

class EditProfileViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityEditProfileBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityEditProfileBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
    }


    private fun setAdapter() {

    }

    private fun setclicks() {

    }

}