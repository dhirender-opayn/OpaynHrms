package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R
import com.example.opaynhrms.adapter.PaySlipListAdapter
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityPayslipBinding
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.ui.Home
import kotlinx.android.synthetic.main.common_toolbar.view.*

class PayslipViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityPayslipBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityPayslipBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()
        setAdapter()
        settoolbar()
    }


    private fun setAdapter() {

        val payslipadapterView = PaySlipListAdapter(baseActivity){

        }
        binder.rvpaysliplist.adapter = payslipadapterView
    }

    private fun settoolbar() {

        binder.toolbar.tvtitle.text = baseActivity.getText(R.string.payslip)
        binder.toolbar.icmenu2.visible()
        binder.toolbar.icmenu.gone()
        binder.toolbar.icmenu2.setImageResource(R.drawable.ic_hamburger)
        binder.toolbar.icmenu2.setOnClickListener {
            (baseActivity as Home).binding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }


    fun setdata()
    {
        binder.name.text = Home.userModel?.data?.user!!.name
        binder.postName.text =Home.userModel?.data!!.user.roles[0].name
        binder.empid.text =  "ID: "+  Home.userModel?.data!!.user.id.toString()

        if (Home.userModel!!.data.user.profile.isNotNull() && Home.userModel!!.data.user.profile.image.isNotNull())
        {

//            Picasso.get().load(Home.userModel!!.data.user.profile.image).placeholder(R.drawable.userwhite).into(binder.ivprofile)
            Glide.with(baseActivity).load(Home.userModel!!.data.user.profile.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .into(binder.ivprofile)

        }
    }


    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

}