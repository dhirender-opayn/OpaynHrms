package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.DetailAttendanceListAdapter
import com.example.opaynhrms.adapter.LeavelistingAdapter
import com.example.opaynhrms.adapter.TotalLeaveStatusAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.LoadImageBindingAdapter
import com.example.opaynhrms.common.CommonActivity
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.isNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.LeaveListJson
import com.example.opaynhrms.model.UserDetailJson
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.repository.UserRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Keys
import com.ieltslearning.listner.ItemClick
import kotlinx.android.synthetic.main.common_toolbar.view.*

class StaffDetailViewModel(application: Application) : AppViewModel(application) ,ItemClick{
    private lateinit var binder: ActivityStaffDetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var userRepository: UserRepository = UserRepository(application)
    val bundle = Bundle()
    private   var attandancelist=ArrayList<UserDetailJson.Data.Attandance>()
    private   var leavelist=ArrayList<UserDetailJson.Data.Leaves>()
     var username=""
    var list = ArrayList<LeaveListJson.Data>()
    fun setBinder(binder: ActivityStaffDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
//        setUserLeaveAdapter()
        leavelist()



        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {

            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            setdata(userdata)
            calluserdata(userdata.id.toString())
        }
        binder.rvtab.adapter = DetailAttendanceListAdapter(baseActivity){

        }

    }
    fun leavelist() {
        userRepository.leavelist(baseActivity) {
            list.clear()
            list.addAll(it.data)

        }
    }



    private  fun calluserdata(userid:String)
    {
        val urlEncoded = Keys.BASEURL+Keys.TEAMDATA+userid
        userRepository.teamdata(baseActivity,urlEncoded)
        {
            if (!it.data.attandances.isNull() ){
                attandancelist.addAll(it.data.attandances)
            }
            if (!it.data.leaves.isNull()){
//                Log.e("popopopopp",it.data.leaves.toString())
                leavelist.addAll(it.data.leaves)
            }

            setattandanceadapter()
        }
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Staff Detail"
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity,R.color.matt_black) )
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
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
//        Log.e("eeededededeeeeee",list.toString())
        val adapter=LeavelistingAdapter(username,baseActivity){
            val _position = it
            baseActivity.bundle.putString(
                Keys.FROM,
                mContext.getString(R.string.leavemanagement)
            )

            baseActivity.bundle.putSerializable(Keys.data, list[_position])
//                bundle.putString(Keys.id, list[position].user_id.toString())
            baseActivity.openA(CommonActivity::class, baseActivity.bundle)
        }
//        adapter.addNewList(leavelist)
        adapter.addNewList(leavelist)
        binder.rvtab.adapter =  adapter
    }

    private fun setUserLeaveAdapter(){


        val totalLeaveStatusAdapter = TotalLeaveStatusAdapter(baseActivity){

        }

        binder.rvUserLeave.adapter = totalLeaveStatusAdapter


    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

        binder.tvAttendance.setOnClickListener{

            binder.rvUserLeave.gone()

            binder.tvAttendance.setBackgroundResource(R.color.purple)
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            binder.tvLeave.setBackgroundResource(R.drawable.rectangle_black_border)
            setattandanceadapter()



        }
        binder.tvLeave.setOnClickListener {
            binder.rvUserLeave.visible()
            binder.tvAttendance.setBackgroundResource(R.drawable.rectangle_black_border)
            binder.tvLeave.setBackgroundResource(R.color.purple)
            binder.tvLeave.setTextColor(ContextCompat.getColor(baseActivity ,R.color.white))
            binder.tvAttendance.setTextColor(ContextCompat.getColor(baseActivity ,R.color.light_gre1))
            leaveadapter()

        }

    }
    override fun onItemViewClicked(position: Int, type: String)
    {
        Log.e("dsdfsdfdsf",type.toString())
        when(type)
        {


//            "3"->{
//                baseActivity. bundle.putString(Keys.FROM,mContext.getString(R.string.leavemanagement))
//                userRepository.leavelist(baseActivity) {
//                    baseActivity. bundle.putSerializable(Keys.data,it.data[position])
//                }
//
////                bundle.putString(Keys.id, list[position].user_id.toString())
//                baseActivity.openA(CommonActivity::class,baseActivity.bundle)
//            }
        }

    }

}