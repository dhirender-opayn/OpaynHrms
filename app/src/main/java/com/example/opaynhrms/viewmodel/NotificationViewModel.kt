package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AnnouncementAdapter
import com.example.opaynhrms.adapter.NotificationAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.repository.LoginRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils.getMultiPart
import com.google.gson.Gson
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.dailog.FilterDailog
import com.example.opaynhrms.dailog.SearchFilterDailog
import com.example.opaynhrms.databinding.ActivityNotificationBinding
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.fragment.AddTicketFragment
import com.example.opaynhrms.fragment.ClockifyWorkListing
import com.example.opaynhrms.fragment.LeaveDetailFragment
import com.example.opaynhrms.listner.FilterListner
import com.example.opaynhrms.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.common_search_bar.view.*
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File
import java.util.ArrayList

class NotificationViewModel(application: Application) : AppViewModel(application), FilterListner {
    private lateinit var binder: ActivityNotificationBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var bundle = Bundle()
    var keyword = ""
    var listr = ArrayList<String>()
    var annoucnemntAdapter: AnnouncementAdapter? = null


    fun setBinder(binder: ActivityNotificationBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        bundle = (mContext as Activity).intent.extras!!

        listr.add("This is ooo data get from the This is dummyda data get from the This is dummyda data get from the")
        listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
        listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
        listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
        listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")

        Log.e("dfdfdsfsdfdsf", bundle.getString(Keys.FROM).toString())
        setclicks()
        setadapter()
        settoolbar()
        searchListner()

//        binder.rvNotification.adapter = AnnouncementAdapter(baseActivity = baseActivity) {
//
//        }
        announcementAdapter()


    }


    private fun searchListner() {
        binder.searchToolbar.search_bar.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                Utils.hideKeyBoard(baseActivity, v)
                keyword = binder.searchToolbar.search_bar.text.toString()
                Log.e("dddddddddddddddddddddd", binder.searchToolbar.search_bar.text.toString())
                val searchword = binder.searchToolbar.search_bar.text.toString()
                Log.e("eeeeeeeeee", searchword.toString())

                val conatiners = listr.filter {
                    it.contains(searchword)

                }
                if (!searchword.isEmpty()){
                    listr.clear()
                    listr.add(conatiners.toString())
                }
                else{
                    listr.clear()
                    listr.add("This is ooo data get from the This is dummyda data get from the This is dummyda data get from the")
                    listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
                    listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
                    listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
                    listr.add("This is dummyda data get from the This is dummyda data get from the This is dummyda data get from the")
                }
                announcementAdapter()


                return@OnEditorActionListener true
            }
            false
        })

//        binder.searchToolbar.search_bar.addTextChangedListener {
//            servicesdetail(it.toString())
//        }
    }

    private fun settoolbar() {

//        binder.toolbar.tvtitle.text = baseActivity.getText(R.string.announcement)
//        binder.toolbar.icmenu2.visible()
//        binder.toolbar.icmenu.gone()
//        binder.toolbar.icmenu2.setImageResource(R.drawable.ic_hamburger)
//        binder.toolbar.icmenu2.setOnClickListener {
//            (baseActivity as Home).binding.drawerLayout.openDrawer(GravityCompat.START)
//        }


        Log.e("dfdffddddddddddd", bundle.getString(Keys.FROM).toString())
        binder.toolbar.tvtitle.text = baseActivity.getText(R.string.announcement)
//        binder.toolbar.tvtitle.setTextColor(baseActivity.resources.getColor(R.color.black) )
        if (bundle.getString(Keys.FROM) == baseActivity.getString(R.string.announcement)) {
            Log.e("dddddddddddddddd", "0000000000000")
            binder.toolbar.icmenu2.gone()
            binder.toolbar.icmenu.visible()

            binder.toolbar.icmenu.setOnClickListener {
                baseActivity.onBackPressed()
            }
        } else {
            Log.e("dddddddd", "111111111111111111")
            binder.toolbar.icmenu.gone()
            binder.toolbar.icmenu2.visible()
            binder.toolbar.icmenu2.setOnClickListener {
                (baseActivity as Home).drawer_layout.openDrawer(GravityCompat.START)
            }

        }
    }


    private fun setadapter() {


        when (bundle.getString(Keys.FROM)) {
            mContext.getString(R.string.announcement) -> {


                announcementAdapter()

//                binder.rvNotification.adapter = AnnouncementAdapter(baseActivity = baseActivity) {
//
//                }


            }
            mContext.getString(R.string.notification) -> {

                binder.rvNotification.adapter = NotificationAdapter(baseActivity = baseActivity) {
                    Log.e("Annoucnemetclicknow", "12132122122121212112")
                }
            }
        }

    }


    private fun announcementAdapter() {

        annoucnemntAdapter = AnnouncementAdapter(baseActivity = baseActivity) {

        }
        binder.rvNotification.adapter = annoucnemntAdapter
        annoucnemntAdapter!!.addNewList(listr)
    }

    private fun setclicks() {

        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
        binder.filter.setOnClickListener {

            val dialog = SearchFilterDailog(baseActivity = baseActivity, this)
            dialog.show(baseActivity.supportFragmentManager, dialog.getTag())
        }
    }

    override fun filterdata(type: String, startdate: String, endate: String) {

    }

}