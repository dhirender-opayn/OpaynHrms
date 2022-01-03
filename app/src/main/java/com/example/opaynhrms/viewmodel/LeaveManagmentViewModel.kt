package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.adapter.LeaveDetailCartAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityLeaveManagementBinding
import com.example.opaynhrms.extensions.showConfirmAlert
import com.example.opaynhrms.model.LeaveListJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.google.gson.JsonObject
import com.ieltslearning.base.AppViewModel
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*


class LeaveManagmentViewModel(application: Application) : AppViewModel(application),ItemClick {
    private lateinit var binder: ActivityLeaveManagementBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var selpos = 0
    val bundle = Bundle()
    var userRepository: UserRepository = UserRepository(application)
    var list=ArrayList<LeaveListJson.Data>()

    fun setBinder(binder: ActivityLeaveManagementBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this

        setclicks()
        setAdapter()
        settoolbar()
        leavelist()

    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = mContext.getString(R.string.leavemanagment)
    }
    private fun setAdapter(){
        val totalLeaveStatusAdapter = TotalLeaveStatusAdapter(baseActivity){

        }
        binder.rvTotalleavestatus.adapter = totalLeaveStatusAdapter


    }
    private  fun leavelistAdapter()
    {
        val leaveDetailCartAdapter = LeaveDetailCartAdapter(baseActivity,this)
        leaveDetailCartAdapter.addNewList(list)
        binder.rvLeaveDatailcart.adapter = leaveDetailCartAdapter
    }


    private  fun leavelist()
    {
        userRepository.leavelist(baseActivity){
            list.clear()
            list.addAll(it.data)
            leavelistAdapter()
        }
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    override fun onItemViewClicked(position: Int, type: String)
    {
        when(type)
        {
            "1"->{
                acceptreject(list[position].user_id.toString(),list[position].id.toString(),"1","Are you sure you want to approve the leave")
            }
            "2"->{
                acceptreject(list[position].user_id.toString(),list[position].id.toString(),"2","Are you sure yoi want to cancel the leave")
            }
        }

    }
    private  fun acceptreject(  user_id:String,id:String,type:String,msg:String)
    {
        baseActivity.showConfirmAlert(msg,"Ok","Cancel",onConfirmed = {
            val jsonobj = JsonObject()
            jsonobj.addProperty(Keys.user_id,user_id)
            jsonobj.addProperty(Keys.id,id)
            jsonobj.addProperty(Keys.status,type)
            userRepository.commonpostwithtoken(baseActivity,Keys.LEAVESTATUS,jsonobj){
                leavelist()

            }

        },onCancel = {
        })

    }

}