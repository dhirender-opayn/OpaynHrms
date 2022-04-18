package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.TicketListingAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.example.opaynhrms.databinding.FragmentAddHolidayBinding
import com.example.opaynhrms.databinding.TicketListingBinding
import com.example.opaynhrms.extensions.*
import com.example.opaynhrms.model.TicketListingJson
import com.example.opaynhrms.repository.AddTicketRepository
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.TimePickerFragment
import com.example.opaynhrms.utils.Utils
import kotlinx.android.synthetic.main.common_toolbar.view.*
import okhttp3.MultipartBody
import java.io.File
import java.security.Key
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TicketListingViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: TicketListingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var file: File? = null

    var bundle = Bundle()
    var ltype = ""
    var applyradio = ""
    var leaveid = ""
    var isdateshow = true
    val addTicketRepository: AddTicketRepository = AddTicketRepository(application)
    var ticketListing = ArrayList<TicketListingJson.Data>()

    fun setBinder(binder: TicketListingBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        bundle = (mContext as Activity).intent.extras!!
        setclicks()
        ticketListingApi()
        settoolbar()

    }



    private fun settoolbar() {
        binder.toolbar.tvtitle.setText(baseActivity.getString(R.string.ticket_listing))
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity, R.color.black))
        binder.toolbar.icmenu2.setImageResource(R.drawable.icback_black)
        binder.toolbar.icmenu2.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    private fun ticketListingApi() {
        addTicketRepository.ticketListing(baseActivity, Utils.AUTHTOKEN) {
            if (it.data.isNotNull()) {
                Log.e("54545454554", it.data.toString())
                ticketListing.addAll(it.data)
                ticketListingAdpter()
            }

        }

    }

    private fun ticketListingAdpter() {
        Log.e("00000000000", ticketListing.toString())
        val ticketAdapter = TicketListingAdapter(baseActivity) {

        }
        ticketAdapter.addNewList(ticketListing)
        binder.rvTicket.adapter = ticketAdapter
    }


    private fun setclicks() {


    }

    override fun onClick(v: View?) {

    }

}