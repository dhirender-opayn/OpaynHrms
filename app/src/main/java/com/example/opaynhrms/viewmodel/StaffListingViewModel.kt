package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.LeaveDetailCartAdapter
import com.example.opaynhrms.adapter.StaffListingAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.databinding.ActivityRequestLeaveBinding
import com.example.opaynhrms.databinding.ActivityStaffListingBinding
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.UserRepository
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*


class StaffListingViewModel(application: Application) : AppViewModel(application)
{
    private lateinit var binder: ActivityStaffListingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var userRepository: UserRepository = UserRepository(application)
    var userlist=ArrayList<UserListJson.Data>()

    var selpos = 0
    val bundle = Bundle()
    fun setBinder(binder: ActivityStaffListingBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        setclicks()
        setAdapter()
        settoolbar()
        callUserApi()

    }


    private fun settoolbar(){
        binder.toolbar.tvtitle.setTextColor(R.color.black)
        binder.toolbar.tvtitle.text = "Staff Listing"
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
    }

    private fun setAdapter(){
        val stafflistingview = StaffListingAdapter(baseActivity){

        }
        stafflistingview.addNewList(userlist)
        binder.rvStaffList.adapter = stafflistingview
    }
    private  fun callUserApi()
    {
        userRepository.userslist(baseActivity){
            userlist.clear()
            userlist.addAll(it.data)
        }
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }

}