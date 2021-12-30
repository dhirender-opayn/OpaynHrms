package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.invisible
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.fragment.HomeFragement
import com.example.opaynhrms.fragment.StatisticsFragment
import com.example.opaynhrms.fragment.ProfileFragment
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.ui.Home.Companion.userModel
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils.AUTHTOKEN
import com.google.gson.Gson
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.bottom_nav_bar.view.*
import kotlinx.android.synthetic.main.nointernetconnection.view.*

class HomeViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityHomeBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    val bundle = Bundle()

    fun setBinder(binder: ActivityHomeBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        //defalut icon set
        binder.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        changeIcon(R.drawable.ic_dashboard_active, R.drawable.ic_statistics, R.drawable.ic_profile)

        setclicks()
        getuserdata()
    }
    fun getuserdata() {
        baseActivity.preferencemanger.getString(Keys.USERDATA).let {
            val gson = Gson()
            var jsondata: String = ""
            jsondata = it.toString()
            userModel = gson.fromJson(jsondata, LoginJson::class.java)


        }
        baseActivity.preferencemanger.getString(Keys.TOKEN).let {

            if (AUTHTOKEN.isEmpty()) {
                AUTHTOKEN = "Bearer "+it.toString()
                 Log.e("tokeeenenne", AUTHTOKEN)
            }
        }

    }
    private fun setclicks() {


        binder.nointernet.reconnectbutton.setOnClickListener {
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                binder.nointernet.gone()
                binder.container.visible()
                when (selpos) {
                    0 -> {

                        binder.bottomNav.home.performClick()
                    }
//                    1 -> {
//                        binder.bottomNav.statistics.performClick()
//                    }
                    2 -> {
                        binder.bottomNav.profile.performClick()
                    }

                }
            }
        }



        binder.bottomNav.home.setOnClickListener {
            selpos = 0

            changeIcon(
                R.drawable.ic_dashboard_active,
                R.drawable.ic_statistics,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                Log.e("chedkdfjhdf", "165615451")
                baseActivity.navigateToFragment(HomeFragement(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }

        }
//        binder.bottomNav.statistics.setOnClickListener {
//            selpos = 1
//            changeIcon(
//                R.drawable.ic_dashboard,
//                R.drawable.ic_statistics_active,
//                R.drawable.ic_profile
//            )
//            if (baseActivity.networkcheck.isNetworkAvailable()) {
//                baseActivity.navigateToFragment(StatisticsFragment(baseActivity), bundle, false)
//
//            } else {
//                nointertnetview()
//            }
//        }


        binder.bottomNav.profile.setOnClickListener {
            selpos = 2
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics,
                R.drawable.ic_profile_active

            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(ProfileFragment(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }
        }


    }

    private fun nointertnetview() {
        binder.nointernet.visible()
        binder.container.invisible()
    }

    private fun changeIcon(home: Int, notification: Int, profile: Int) {

        binder.bottomNav.home.setImageResource(home)
//        binder.bottomNav.statistics.setImageResource(notification)
        binder.bottomNav.profile.setImageResource(profile)


    }
}