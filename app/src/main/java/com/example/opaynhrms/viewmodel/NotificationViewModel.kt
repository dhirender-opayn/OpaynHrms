package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AnnouncementAdapter
import com.example.opaynhrms.adapter.NotificationAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.extensions.hideKeyboard
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils.getMultiPart
import com.google.gson.Gson
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import com.example.opaynhrms.fragment.AddTicketFragment
import com.example.opaynhrms.fragment.ClockifyWorkListing
import com.example.opaynhrms.fragment.LeaveDetailFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File
import java.util.ArrayList

class NotificationViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityNotificationBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var bundle = Bundle()


    fun setBinder(binder: ActivityNotificationBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        bundle = (mContext as Activity).intent.extras!!
        setclicks()
        setadapter()

    }



    private fun setadapter() {


        when (bundle.getString(Keys.FROM)) {
            mContext.getString(R.string.announcement) -> {
                Log.e("Annoucnemetclicknow", "0000000000000000000000")
                binder.toolbar.tvtitle.text = mContext.getString(R.string.announcement)
                binder.rvNotification.adapter = AnnouncementAdapter(baseActivity = baseActivity) {

                }

            }
            mContext.getString(R.string.notification) -> {
                binder.toolbar.tvtitle.text = mContext.getString(R.string.notification)
                binder.rvNotification.adapter = NotificationAdapter(baseActivity = baseActivity) {
                    Log.e("Annoucnemetclicknow", "12132122122121212112")
                }
            }

        }

    }


    private fun setclicks() {

        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }


    }

}