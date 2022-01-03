package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.DetailAttendanceListAdapter
import com.example.opaynhrms.adapter.LeavelistingAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.LoadImageBindingAdapter
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.model.UserDetailJson
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.utils.Keys
import kotlinx.android.synthetic.main.common_toolbar.view.*

class StaffDetailViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityStaffDetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var userRepository: UserRepository = UserRepository(application)
    val bundle = Bundle()
    private   var attandancelist=ArrayList<UserDetailJson.Data.Attandance>()
    private   var leavelist=ArrayList<UserDetailJson.Data.Leaves>()
    var username=""
    fun setBinder(binder: ActivityStaffDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()

        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {

            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            setdata(userdata)
            calluserdata(userdata.id.toString())
        }
        binder.rvtab.adapter = DetailAttendanceListAdapter(baseActivity){

        }

    }
    private  fun calluserdata(userid:String)
    {
        val urlEncoded = Keys.BASEURL+Keys.TEAMDATA+userid
        userRepository.teamdata(baseActivity,urlEncoded){
            attandancelist.addAll(it.data.attandances)
            leavelist.addAll(it.data.leaves)
            setattandanceadapter()
        }
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Staff Detail"
    }
    private  fun setdata(data :UserListJson.Data)
    {
        if (data.profile.isNotNull() && data.profile.image.isNotNull())
        {
            LoadImageBindingAdapter.roundedloadImage(binder.ivprofile,data.profile.image,1)
        }
        username=data.name
        binder.staffname.text=data.name
        binder.post.text=data.roles[0].name
    }


    private fun setAdapter() {

    }
    private  fun setattandanceadapter()
    {
        val adapter=DetailAttendanceListAdapter(baseActivity){

        }
        adapter.addNewList(attandancelist)
        binder.rvtab.adapter = adapter
    }
    private  fun leaveadapter()
    {
        val adapter=LeavelistingAdapter(username,baseActivity){

        }
        adapter.addNewList(leavelist)
        binder.rvtab.adapter =  adapter
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

        binder.tvAttendance.setOnClickListener{

            binder.tvAttendance.setBackgroundResource(R.color.pinky_red)
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            binder.tvLeave.setBackgroundResource(R.color.white)
            setattandanceadapter()



        }
        binder.tvLeave.setOnClickListener {
            binder.tvAttendance.setBackgroundResource(R.color.white)
            binder.tvLeave.setBackgroundResource(R.color.pinky_red)
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            leaveadapter()

        }

    }

}