package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import com.example.opaynhrms.R


import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentHomeFragementBinding
import com.example.opaynhrms.databinding.FragmentProfileBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.repository.HomeRepository
import com.example.opaynhrms.ui.Home

import com.ieltslearning.base.AppViewModel
import com.squareup.picasso.Picasso


class ProfileViewModel(application: Application) : AppViewModel(application) {
    var msg: String = ""
    var loginSigupRepository: HomeRepository = HomeRepository(application)
    private lateinit var binder: FragmentProfileBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: FragmentProfileBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        setdata()

    }

    private fun setdata() {
        binder.name.text = Home.userModel?.data?.user!!.name

        if (Home.userModel!!.data.user.profile.isNotNull() && Home.userModel!!.data.user.profile.image.isNotNull()) {
            Picasso.get().load(Home.userModel!!.data.user.profile.image)
                .placeholder(R.drawable.userwhite).into(binder.ivprofile)
        }

    }


}