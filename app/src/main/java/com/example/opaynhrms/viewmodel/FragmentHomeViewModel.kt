package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import com.example.opaynhrms.R


import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentHomeFragementBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.repository.HomeRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject

import com.example.opaynhrms.base.AppViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.common_toolbar.view.*


class FragmentHomeViewModel(application: Application) : AppViewModel(application)
{
    var msg: String = ""
    var homeRepository: HomeRepository = HomeRepository(application)
    private lateinit var binder: FragmentHomeFragementBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context


    fun setBinder(binder: FragmentHomeFragementBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        setdata()
        settoolbar()
    }
    private fun settoolbar()
    {
        binder.toolbar.tvtitle.text = "Home"
        binder.toolbar.icmenu.gone()
    }

    private fun setdata()
    {
        binder.name.text = Home.userModel?.data?.user!!.name

        if (Home.userModel!!.data.user.profile.isNotNull() && Home.userModel!!.data.user.profile.image.isNotNull())
        {
            Picasso.get().load(Home.userModel!!.data.user.profile.image).placeholder(R.drawable.userwhite).into(binder.ivprofile)
        }
    }
    fun addorupdateAttandance(lat:String,lng:String,time:String,inout:String)
    {
        val jsonObject=JsonObject()
        jsonObject.addProperty(Keys.lat,lat)
        jsonObject.addProperty(Keys.lng,lng)
        jsonObject.addProperty(Keys.time,time)
        jsonObject.addProperty(Keys.type,inout)
        homeRepository.commonpostwithtoken(baseActivity,Keys.ATTANDANCE,jsonObject){

        }
    }



}