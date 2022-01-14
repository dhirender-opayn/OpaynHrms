package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.ClockifyTaskListingAdapter
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragementClockifyworkListingBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.ClockifyListJson
import com.example.opaynhrms.repository.HomeRepository
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.common_toolbar.view.*

class ClockifyListingViewModel(application: Application) : AppViewModel(application),View.OnClickListener
{
    private lateinit var binder: FragementClockifyworkListingBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var clockifyAdapterView : ClockifyTaskListingAdapter?=null
    val bundle = Bundle()
    var arrayList=ArrayList<ClockifyListJson.Data>()
    var homeRepository: HomeRepository = HomeRepository(application)
    var totalpage=0
    var page=1
    var asc_des=""
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    private lateinit var skeleton2: Skeleton

    fun setBinder(binder: FragementClockifyworkListingBinding, baseActivity: KotlinBaseActivity)
    {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        settoolbar()
        setclick()

        setAdapter()
        clockfylist()
        scrolllistner()
    }

    private fun setAdapter() {
          clockifyAdapterView = ClockifyTaskListingAdapter(baseActivity) {

        }
        binder.rvClockify.adapter = clockifyAdapterView

    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity,R.color.light_gre1))
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.tvtitle.text = mContext.getString(R.string.work_history)
    }

    private fun setclick() {
        binder.toolbar.icmenu.setOnClickListener(this)
    }
    private  fun scrolllistner()
    {
        val linearLayoutManager = LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false)
        binder.rvClockify.setLayoutManager(linearLayoutManager);
        binder.rvClockify.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.childCount
                    totalItemCount = linearLayoutManager.itemCount
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            ++page
                            binder.idPBLoading.visible()
                            loading = false
                            clockfylist()

                            Log.v("...", "Last Item Wow !")
                            // Do pagination.. i.e. fetch new data
                            // loading = true
                        }
                    }
                }
            }
        })
    }
    private  fun  clockfylist()
    {
        homeRepository.clockfylisting(baseActivity,page.toString()){
            arrayList.addAll(it.data)
             binder.idPBLoading.gone()
            clockifyAdapterView?.addToList(arrayList)
            clockifyAdapterView?.notifyDataSetChanged()

        }
    }


    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.icmenu -> {

                baseActivity.onBackPressed()
            }

        }
    }
}