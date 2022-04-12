package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
 import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
 import com.example.opaynhrms.adapter.StaffListingAdapter
 import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
 import com.example.opaynhrms.databinding.ActivityStaffListingBinding
import com.example.opaynhrms.extensions.showConfirmAlert
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
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
//        setAdapter()
        settoolbar()


    }


    private fun settoolbar()
    {
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity,R.color.black))
        binder.toolbar.tvtitle.text = "Staff Listing"
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
    }

    private fun setAdapter(){
        val stafflistingview = StaffListingAdapter(baseActivity){
            baseActivity.showConfirmAlert("Are you sure you want to delete the user","Ok","Cancel",onCancel = {
            },onConfirmed = {
                deletuser(userlist[it].id.toString())
            })
        }
        stafflistingview.addNewList(userlist)
        binder.rvStaffList.adapter = stafflistingview
    }
    fun callUserApi()
    {
        userRepository.userslist(baseActivity){
            userlist.clear()
            userlist.addAll(it.data)
            setAdapter()
        }
    }
    private  fun deletuser( id: String)
    {
        val  jsonobject=JsonObject()
        jsonobject.addProperty(Keys.id,id)
        userRepository.deleteRequestBody(baseActivity,Keys.DELTEUSER,jsonobject){
            callUserApi()
        }
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }

}