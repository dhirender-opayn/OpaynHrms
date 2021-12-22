package com.example.opaynhrms.viewmodel

import android.app.Application
 import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.invisible
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.fragment.HomeFragement
import com.example.opaynhrms.fragment.NotificationFragment
import com.example.opaynhrms.fragment.ProfileFragment
import com.example.opaynhrms.fragment.SettingFragment
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

        setclicks()
    }

    private fun setclicks() {

//        binder.showDrawer.tvprepration.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//
//        }

//        binder.showDrawer.tvallcourse.setOnClickListener() {
//            binder.drawerLayout.closeDrawers()
//            binder.bottomNav.library.performClick()
//            // baseActivity.openA(CourseList::class)
//        }
//        binder.showDrawer.tvdashboard.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            binder.bottomNav.profile.performClick()
//
//            //            baseActivity.openA(Dashboard::class)
//        }
//        binder.showDrawer.ivedit.setOnClickListener() {
//            binder.drawerLayout.closeDrawers()
//            baseActivity.openA(EditProfile::class)
//        }
//        binder.showDrawer.tvsetting.setOnClickListener() {
//            binder.drawerLayout.closeDrawers()
//            baseActivity.openA(Setting::class)
//        }
//
//
//        binder.showDrawer.tvnotification.setOnClickListener() {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FRAGMENT_TYPE, Keys.NOTIFICATIONFRAG.toString())
//            baseActivity.openA(CommonActivity::class, this.bundle)
//        }
//
//
//        binder.showDrawer.tvfab.setOnClickListener() {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FRAGMENT_TYPE, Keys.FAVOURITELISTFRAG.toString())
//            baseActivity.openA(CommonActivity::class, this.bundle)
//        }
//
//
//
//
//        binder.showDrawer.tvfaq.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            baseActivity.openA(Faq::class)
//        }
//        binder.showDrawer.tvaboutus.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FROM, baseActivity.getString(R.string.aboutus))
//            baseActivity.openA(Aboutus::class, bundle)
//        }
//        binder.showDrawer.tvprivacy_Policy.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FROM, baseActivity.getString(R.string.privacypolicy))
//            baseActivity.openA(Aboutus::class, bundle)
//        }
//        binder.showDrawer.tv_term_condition.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FROM, baseActivity.getString(R.string.termsconditions))
//            baseActivity.openA(Aboutus::class, bundle)
//        }
//        binder.showDrawer.tvcontactus.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            bundle.putString(Keys.FROM, "Contact Us")
//            baseActivity.openA(ContactUs::class, bundle)
//        }
//
//
//        binder.showDrawer.llfreetrial.setOnClickListener {
//            binder.showDrawer.ivservice.setImageResource(R.drawable.ivdown)
//            if (freetrial) {
//                freetrial = false
//                binder.showDrawer.ivfreetrail.setImageResource(R.drawable.ivup)
//                binder.showDrawer.llfreetrial_container.visible()
//
//            } else {
//                freetrial = true
//                binder.showDrawer.ivfreetrail.setImageResource(R.drawable.ivdown)
//                binder.showDrawer.llfreetrial_container.gone()
//            }
//        }
//
//        binder.showDrawer.llservice.setOnClickListener {
//
//
//            if (service) {
//                service = false
//                binder.showDrawer.ivservice.setImageResource(R.drawable.ivup)
//
//
//            } else {
//                service = true
//                binder.showDrawer.ivservice.setImageResource(R.drawable.ivdown)
//
//
//            }
//        }
//
//        binder.showDrawer.llmocktest.setOnClickListener {
//            if (service) {
//                service = false
//                binder.showDrawer.iv_mock_test.setImageResource(R.drawable.ivup)
//
//
//            } else {
//                service = true
//                binder.showDrawer.iv_mock_test.setImageResource(R.drawable.ivdown)
//
//
//            }
//        }
//
//        binder.showDrawer.tvprepration.setOnClickListener {
//            binder.drawerLayout.closeDrawers()
//            //  baseActivity.openA(IeltsPreprations::class)
//        }
//        binder.showDrawer.tv_login.setOnClickListener {
//            baseActivity.openA(Login::class)
//        }
//        binder.showDrawer.tvieltspreference.setOnClickListener {
//            val dialog = ReadingTypeDailog(baseActivity, "")
//            dialog.show(baseActivity.supportFragmentManager, dialog.getTag())
//        }


        binder.nointernet.reconnectbutton.setOnClickListener {
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                binder.nointernet.gone()
                binder.container.visible()
                when (selpos) {
                    0 -> {
                        binder.bottomNav.home.performClick()
                    }
                    1 -> {
                        binder.bottomNav.notification.performClick()
                    }
                    2 -> {
                        binder.bottomNav.setting.performClick()
                    }
                    3 -> {
                        binder.bottomNav.profile.performClick()

                    }


                }
            }
        }



        binder.bottomNav.home.setOnClickListener {
            selpos = 0

            changeIcon(
                R.drawable.ic_dashboard_active,
                R.drawable.ic_notifications,
                R.drawable.ic_drawable_setting,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(HomeFragement(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }

        }
        binder.bottomNav.notification.setOnClickListener {
            selpos = 1
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_notifications_active,
                R.drawable.ic_drawable_setting,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(NotificationFragment(baseActivity), bundle, false)

            } else {
                nointertnetview()
            }
        }
        binder.bottomNav.setting.setOnClickListener {
            selpos = 2
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_notifications,
                R.drawable.ic_drawable_setting_active,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(SettingFragment(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }
        }

        binder.bottomNav.profile.setOnClickListener {
            selpos = 3
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_notifications,
                R.drawable.ic_drawable_setting,
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

    private fun changeIcon(home: Int, notification: Int, setting: Int, profile: Int) {

        binder.bottomNav.home.setImageResource(home)
        binder.bottomNav.notification.setImageResource(notification)
        binder.bottomNav.setting.setImageResource(setting)
        binder.bottomNav.profile.setImageResource(profile)


    }
}