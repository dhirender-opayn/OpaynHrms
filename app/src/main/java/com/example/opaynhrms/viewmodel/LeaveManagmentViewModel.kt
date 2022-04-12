package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.util.Util
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.LeaveDetailCartAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.common.CommonActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.extensions.showConfirmAlert
import com.example.opaynhrms.model.LeaveListJson
import com.example.opaynhrms.model.UserLeaveDetailJson
import com.example.opaynhrms.repository.LeaveManagementRepository
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.example.opaynhrms.utils.Utils
import com.google.gson.JsonObject

import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*


class LeaveManagmentViewModel(application: Application) : AppViewModel(application), ItemClick {
    private lateinit var binder: ActivityLeaveManagementBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    var leaveManagementRepository: LeaveManagementRepository =
        LeaveManagementRepository(application)
    var userRepository: UserRepository = UserRepository(application)
    var list = ArrayList<LeaveListJson.Data>()
    var userLeaveJson = ArrayList<UserLeaveDetailJson.Data.Original.Data>()


    fun setBinder(binder: ActivityLeaveManagementBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        setclicks()
//        setAdapter()
        settoolbar()
        leavelist()
        userLeaveDetailApi()

    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = mContext.getString(R.string.leavemanagment)
    }

    private fun setAdapter() {
        val totalLeaveStatusAdapter = TotalLeaveStatusAdapter(baseActivity) {

        }
        Log.e("checkTotalLeave",userLeaveJson.toString())
        totalLeaveStatusAdapter.addNewList(userLeaveJson)
        binder.rvTotalleavestatus.adapter = totalLeaveStatusAdapter


    }

    private fun leavelistAdapter() {
        val leaveDetailCartAdapter = LeaveDetailCartAdapter(baseActivity, this)
        leaveDetailCartAdapter.addNewList(list)
        binder.rvLeaveDatailcart.adapter = leaveDetailCartAdapter
    }


    fun leavelist() {
        userRepository.leavelist(baseActivity) {
            list.clear()
            list.addAll(it.data)
            leavelistAdapter()
        }
    }

    private fun userLeaveDetailApi() {
//        val jsonObject = JsonObject()
//        jsonObject.addProperty(Keys.user_id, Home.userModel?.data?.user?.id)
        leaveManagementRepository.getLeaveByUser(baseActivity, Utils.AUTHTOKEN, ) {
            val leaveID = it.data.original.data.forEach { it.leave_Category_id }
            userLeaveJson.addAll(it.data.original.data)
            Log.e("checkMatchHolidas", it.data.original.data.toString())

            if (Home.userModel?.data?.user!!.roles[0].name.equals(Utils.SUPERADMIN)){

            } else{

                setAdapter()
            }


            //for matching data
//            Home.categoryTypeListingJson?.data!!.forEach {
//                if (it.id.equals(leaveID)){
//                    Log.e("checkMatchHolidas",it.category + "  andID is "+it.id.toString())
//                }
//            }


        }
    }


    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    override fun onItemViewClicked(position: Int, type: String) {
        when (type) {
            "1" -> {
                acceptreject(
                    list[position].user_id.toString(),
                    list[position].id.toString(),
                    list[position].leave_category_id.toString(),
                    "1",
                    "Are you sure you want to approve the leave"
                )
            }
            "2" -> {
                acceptreject(
                    list[position].user_id.toString(),
                    list[position].id.toString(),
                    list[position].leave_category_id.toString(),
                    "2",
                    "Are you sure yoi want to cancel the leave"
                )
            }
            "3" -> {
                baseActivity.bundle.putString(
                    Keys.FROM,
                    mContext.getString(R.string.leavemanagement)
                )
                baseActivity.bundle.putSerializable(Keys.data, list[position])
//                bundle.putString(Keys.id, list[position].user_id.toString())
                baseActivity.openA(CommonActivity::class, baseActivity.bundle)
            }
        }

    }

    private fun acceptreject(
        user_id: String,
        id: String,
        cateogory_id: String,
        type: String,
        msg: String
    ) {
        baseActivity.showConfirmAlert(msg, "Ok", "Cancel", onConfirmed = {
            val jsonobj = JsonObject()
            jsonobj.addProperty(Keys.user_id, user_id)
            jsonobj.addProperty(Keys.id, id)
            jsonobj.addProperty(Keys.status, type)
            jsonobj.addProperty(Keys.leave_category_id2, cateogory_id)

            userRepository.commonpostwithtoken(baseActivity, Keys.LEAVESTATUS, jsonobj) {
                leavelist()

            }

        }, onCancel = {
        })

    }


}