package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.AttendanceListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.dailog.FilterDailog
import com.example.opaynhrms.databinding.ActivityAttendenceListBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.model.AttandanceListJson
import com.example.opaynhrms.repository.HomeRepository
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.example.opaynhrms.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class AttendenceListViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityAttendenceListBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var totalpage = 0
    var page = 1
    var homeRepository: HomeRepository = HomeRepository(application)
    private lateinit var skeleton2: Skeleton
    var attandancelist = ArrayList<AttandanceListJson.Data.Data>()
    var attandanceadapter: AttendanceListAdapter? = null

    fun setBinder(binder: ActivityAttendenceListBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
        showskelton()
        attandancelist()
        scrolllistner()
    }

    private fun showskelton() {
        if (baseActivity.networkcheck.isNetworkAvailable()) {
            skeleton2 = binder.skeletonLayout
            skeleton2 = binder.rvAttendenceList.applySkeleton(R.layout.item_attendance_list)
            //   skeleton = binding.packagesContainer.createSkeleton()
            //   skeleton.showSkeleton()
            skeleton2.showSkeleton()
        }
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.setTextColor(ContextCompat.getColor(baseActivity, R.color.black))
        binder.toolbar.icmenu.setImageResource(R.drawable.icback_black)
        binder.toolbar.ivcart.visible()
        binder.toolbar.ivcart.setImageResource(R.drawable.ic_baseline_filter_alt_24)
        binder.toolbar.tvtitle.text = baseActivity.getString(R.string.attandancelist)
    }

    private fun setAdapter() {
        attandanceadapter = AttendanceListAdapter(baseActivity) {

        }
        binder.rvAttendenceList.adapter = attandanceadapter


    }

    private fun scrolllistner() {


        val linearLayoutManager =
            LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false)
        binder.rvAttendenceList.setLayoutManager(linearLayoutManager);


        binder.rvAttendenceList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.childCount
                    totalItemCount = linearLayoutManager.itemCount
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false

                            if (page < totalpage) {
                                Log.e("hehehehe", page.toString() + " " + totalpage.toString())
                                binder.rvprogress.visible()
                                page++
                                attandancelist()

                            }
                            Log.v("...", "Last Item Wow !")
                            // Do pagination.. i.e. fetch new data
                            // loading = true
                        }
                    }
                }
            }
        })


    }

    private fun attandancelist() {
        homeRepository.attandancelist(baseActivity, page.toString(), "", "", "") {
            loading = true
            binder.rvprogress.gone()
            onDataLoaded()
            if (totalpage.equals(0)) {
                totalpage = it.data.last_page
            }
            attandancelist.addAll(it.data.data)
            attandanceadapter?.addNewList(attandancelist)
            attandanceadapter?.notifyDataSetChanged()
        }
    }

    private fun onDataLoaded() {
        // skeleton.showOriginal()
        skeleton2.showOriginal()
    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
        binder.toolbar.ivcart.setOnClickListener {
            val dialog= FilterDailog( baseActivity = baseActivity)
            dialog.show(baseActivity.supportFragmentManager, dialog.getTag())

        }

    }
}