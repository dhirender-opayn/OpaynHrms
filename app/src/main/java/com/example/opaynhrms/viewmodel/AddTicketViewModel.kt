package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.ClockifyTaskListingAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragementClockifyworkListingBinding
import com.example.opaynhrms.databinding.FragmentAddticketBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.repository.AddTicketRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AddTicketViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: FragmentAddticketBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    var addTicketRepository: AddTicketRepository = AddTicketRepository(application)

    fun setBinder(binder: FragmentAddticketBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setclick()

    }


    private fun addTicketApi() {
        var jsonObject = JsonObject()
        jsonObject.addProperty(Keys.user_id, Home.userModel?.data?.user!!.id)
        jsonObject.addProperty(Keys.name, Home.userModel?.data?.user!!.name)
        jsonObject.addProperty(Keys.email, Home.userModel?.data?.user!!.email)
        if (Home.userModel?.data?.user!!.mobile.isNotNull()) {
            jsonObject.addProperty(Keys.mobile, Home.userModel?.data?.user!!.mobile)
        }
        jsonObject.addProperty(Keys.subject, binder.tvTitle.text.toString().trim())
        jsonObject.addProperty(Keys.description, binder.tvAnnouncement.text.toString().trim())



        addTicketRepository.addTicket(baseActivity, jsonObject) {
            binder.tvTitle.setText("")
            binder.tvAnnouncement.setText("")
            baseActivity.stopProgressDialog()
        }


    }

    private fun settoolbar() {
//        binder.toolbar.tvtitle.setTextColor(mContext.getColor(R.color.light_gre1))
//        binder.toolbar.tvtitle.text = mContext.getString(R.string.add_ticket)
    }

    private fun setclick() {
        binder.toolbar.icmenu.setOnClickListener(this)
        binder.loginbtn.setOnClickListener(this)
    }

    private fun validation(): Boolean {
        if (binder.tvTitle.isNull()) {
            baseActivity.customSnackBar("Please Enter Title", true)
            return false
        }
        if (binder.tvAnnouncement.isNull()) {
            baseActivity.customSnackBar("Please Enter Description", true)
            return false
        }
        return true
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {
                baseActivity.onBackPressed()
            }
            R.id.loginbtn -> {
                if (validation()) {
                    baseActivity.startProgressDialog()
                    addTicketApi()
                }

            }

        }
    }
}