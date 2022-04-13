package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.PaySlipListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityHomeBinding
import com.example.opaynhrms.fragment.HomeFragement
import com.example.opaynhrms.fragment.ProfileFragment
import com.example.opaynhrms.model.LoginJson
import com.example.opaynhrms.ui.Home.Companion.userModel
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils.AUTHTOKEN
import com.google.gson.Gson
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.common.CommonActivity
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.fragment.AddHoliday
import com.example.opaynhrms.fragment.ReportingFragment
import com.example.opaynhrms.repository.RequestRepository
import com.example.opaynhrms.ui.EditProfile
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.ui.Notification
import com.example.opaynhrms.ui.Payslip
import com.example.opaynhrms.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_nav_bar.view.*
import kotlinx.android.synthetic.main.nointernetconnection.view.*
import kotlinx.android.synthetic.main.withlogin_layout.view.*

class HomeViewModel(application: Application) : AppViewModel(application) {
    var requestRepository: RequestRepository = RequestRepository(application)
    private lateinit var binder: ActivityHomeBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    var bundle = Bundle()

    fun setBinder(binder: ActivityHomeBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        //defalut icon set
        binder.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        changeIcon(
            R.drawable.ic_dashboard_black_active,
            R.drawable.ic_statistics,
            R.drawable.ic_loudspeaker,
            R.drawable.ic_payroll_salary,
            R.drawable.ic_bottom_add_holiday,
            R.drawable.ic_profile,

        )
        setclicks()
        getuserdata()
        checkuser()
        setSideMenuClick()
        leaveTypeApi()
        leaveCategory()
    }

    private fun leaveTypeApi() {
        requestRepository.leaveType(baseActivity) {
            if (it.data.isNotNull()) {
                val gson = Gson()
                val json = gson.toJson(it)
                Home.leaveTypeListingJson = it

            }
        }
    }

    private fun leaveCategory() {
        requestRepository.leaveCategory(baseActivity) {
            if (it.data.isNotNull()) {
                Home.categoryTypeListingJson = it

            }
        }
    }


    private fun checkuser() {
        if (Home.userModel?.data?.user!!.roles.size > 0) {
            val admin = Home.userModel?.data?.user!!.roles[0].name

            if (admin.equals(Utils.SUPERADMIN)) {
                binder.bottomNav.statistics.visible()
                binder.bottomNav.ivannouncement.gone()
                binder.bottomNav.salary.gone()
                binder.bottomNav.addHoliday.visible()


            } else {
                binder.bottomNav.statistics.gone()
                binder.bottomNav.ivannouncement.visible()
                binder.bottomNav.salary.visible()
                binder.bottomNav.addHoliday.gone()


            }
        }
    }


    fun getuserdata() {
        baseActivity.preferencemanger.getString(Keys.USERDATA).let {
            val gson = Gson()
            var jsondata: String = ""
            jsondata = it.toString()
            userModel = gson.fromJson(jsondata, LoginJson::class.java)
            binder.showDrawer.tvname.text = userModel?.data!!.user.name.capitalizedFirstLetter()
            if (userModel!!.data.user.isNotNull()){
                binder.showDrawer.tvrank.text = userModel?.data!!.user.roles[0].name
            } else {
                binder.showDrawer.tvrank.text = userModel?.data!!.user.email
            }

            Log.e("tokeeenenne", userModel.toString())

            if (userModel!!.data.user.profile.isNotNull() && userModel!!.data.user.profile.image.isNotNull())
            {
//                Picasso.get().load(Home.userModel!!.data.user.profile.image).placeholder(R.drawable.userwhite).into(binder.ivprofile)
                Glide.with(baseActivity).load(userModel!!.data.user.profile.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    .into(binder.showDrawer.ivuserprofile)
            }



        }
        baseActivity.preferencemanger.getString(Keys.TOKEN).let {

            if (AUTHTOKEN.isEmpty()) {
                AUTHTOKEN = "Bearer " + it.toString()
                Log.e("tokeeenenne", AUTHTOKEN)
            }
        }

    }




    private fun setSideMenuClick() {
        binder.showDrawer.ivedit.setOnClickListener {
            baseActivity.openA(EditProfile::class)
        }
        binder.showDrawer.ivuserprofile.setOnClickListener {
            baseActivity.openA(EditProfile::class)
        }



        binder.showDrawer.tvhome.setOnClickListener {

            binder.drawerLayout.closeDrawers()
            binder.bottomNav.home.performClick()
        }
        binder.showDrawer.tvprofile.setOnClickListener {

            binder.drawerLayout.closeDrawers()
            binder.bottomNav.profile.performClick()
        }


        binder.showDrawer.tvAnnouncement.setOnClickListener {
            binder.drawerLayout.closeDrawers()
            binder.bottomNav.ivannouncement.performClick()
        }
        binder.showDrawer.tvsalary.setOnClickListener {
            binder.drawerLayout.closeDrawers()
            binder.bottomNav.salary.performClick()
        }
        binder.showDrawer.tvfaq.setOnClickListener {
            binder.drawerLayout.closeDrawers()
            bundle.putString(Keys.FROM,Keys.FAQ)
            baseActivity.openA(CommonActivity::class, this.bundle)

        }

        binder.showDrawer.tvlogout.setOnClickListener {
            baseActivity.logout()
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
                    1 -> {
                        binder.bottomNav.ivannouncement.performClick()
                    }
                    2 -> {
                        binder.bottomNav.statistics.performClick()
                    }
                    3 -> {
                        binder.bottomNav.salary.performClick()
                    }
                    4 -> {
                        binder.bottomNav.addHoliday.performClick()
                    }
                    5 -> {
                        binder.bottomNav.profile.performClick()
                    }

                }
            }
        }



        binder.bottomNav.home.setOnClickListener {
            selpos = 0

            changeIcon(
//                R.drawable.ic_dashboard_active,
                R.drawable.ic_dashboard_black_active,
                R.drawable.ic_statistics,
                R.drawable.ic_loudspeaker,
                R.drawable.ic_payroll_salary,
                R.drawable.ic_bottom_add_holiday,
                R.drawable.ic_profile,

            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                Log.e("chedkdfjhdf", "165615451")
                baseActivity.navigateToFragment(HomeFragement(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }

        }

        binder.bottomNav.ivannouncement.setOnClickListener {
            selpos = 1

            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics,
                R.drawable.ic_loudspeaker_active,
                R.drawable.ic_payroll_salary,
                R.drawable.ic_bottom_add_holiday,
                R.drawable.ic_profile,

            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                bundle.putString(Keys.FROM, baseActivity.getString(R.string.notification))
                baseActivity.navigateToFragment(Notification(baseActivity), bundle, false)
            } else {
                nointertnetview()
            }
        }




        binder.bottomNav.statistics.setOnClickListener {
            selpos = 2
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics_black_active,
                R.drawable.ic_loudspeaker,
                R.drawable.ic_payroll_salary,
                R.drawable.ic_bottom_add_holiday,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(ReportingFragment(baseActivity), bundle, false)

            } else {
                nointertnetview()
            }
        }

        binder.bottomNav.salary.setOnClickListener {
            selpos = 3
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics,
                R.drawable.ic_loudspeaker,
                R.drawable.ic_payroll_salary_active,
                R.drawable.ic_bottom_add_holiday,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                baseActivity.navigateToFragment(Payslip(baseActivity), bundle, false)

            } else {
                nointertnetview()
            }
        }

        binder.bottomNav.addHoliday.setOnClickListener {
            selpos = 4
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics,
                R.drawable.ic_loudspeaker,
                R.drawable.ic_payroll_salary,
                R.drawable.ic_bottom_add_holiday_active,
                R.drawable.ic_profile
            )
            if (baseActivity.networkcheck.isNetworkAvailable()) {
                bundle.putString(Keys.FROM,baseActivity.getString(R.string.home))
                 baseActivity.navigateToFragment(AddHoliday(baseActivity), bundle, false)

            } else {
                nointertnetview()
            }
        }



        binder.bottomNav.profile.setOnClickListener {
            selpos = 5
            changeIcon(
                R.drawable.ic_dashboard,
                R.drawable.ic_statistics,
                R.drawable.ic_loudspeaker,
                R.drawable.ic_payroll_salary,
                R.drawable.ic_bottom_add_holiday,
                R.drawable.ic_profile_black_active

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

    private fun changeIcon(home: Int, notification: Int, announcement: Int,salary:Int,addholiday:Int,  profile: Int) {

        binder.bottomNav.home.setImageResource(home)
        binder.bottomNav.statistics.setImageResource(notification)
        binder.bottomNav.ivannouncement.setImageResource(announcement)
        binder.bottomNav.salary.setImageResource(salary)
        binder.bottomNav.addHoliday.setImageResource(addholiday)
        binder.bottomNav.profile.setImageResource(profile)


    }
}