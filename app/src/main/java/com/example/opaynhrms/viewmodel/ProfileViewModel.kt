package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.opaynhrms.R


import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.FragmentProfileBinding
import com.example.opaynhrms.extensions.gone
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.extensions.visible
import com.example.opaynhrms.repository.HomeRepository
import com.example.opaynhrms.ui.Home
import com.example.opaynhrms.utils.Utils

import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.common.CommonActivity
import com.example.opaynhrms.ui.TicketListing
import com.example.opaynhrms.utils.Keys
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.withlogin_layout.view.*


class ProfileViewModel(application: Application) : AppViewModel(application) {
    var msg: String = ""
    var loginSigupRepository: HomeRepository = HomeRepository(application)
    private lateinit var binder: FragmentProfileBinding
    lateinit var baseActivity: KotlinBaseActivity
    private lateinit var mContext: Context
    fun setBinder(binder: FragmentProfileBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        setdata()
        contentvisibily()
        settoolbar()

    }





    private fun settoolbar() {

        binder.toolbar.tvtitle.text = baseActivity.getText(R.string.profile)
        binder.toolbar.icmenu2.visible()
        binder.toolbar.icmenu.gone()
        binder.toolbar.icmenu2.setImageResource(R.drawable.ic_hamburger)
        binder.toolbar.icmenu2.setOnClickListener {
            (baseActivity as Home).binding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }


      fun setdata() {
        binder.name.text = Home.userModel?.data?.user!!.name

              if (Home.userModel?.data?.user!!.roles.size > 0) {

                  if (Home.userModel?.data?.user!!.roles[0].name.equals(Utils.SUPERADMIN)) {
                      Log.e("ddfdfdfdf","ddfdfdfdfdfd")
                      binder.addTicket.setText(baseActivity.getString(R.string.ticket_listing))
                      binder.addTicket.setOnClickListener {
                          baseActivity.openA(TicketListing::class)
                      }

                  } else{
                      Log.e("ddfdfdfdf","545545454545")
                      binder.addTicket.setText(baseActivity.getString(R.string.add_ticket))
                      var bundle = Bundle()
                      bundle.putString(Keys.FROM, baseActivity.getString(R.string.add_ticket))
                      binder.addTicket.setOnClickListener {
                          baseActivity.openA(CommonActivity::class, bundle)
                      }

                  }

              }



          if (Home.userModel!!.data.user.profile.isNotNull() && Home.userModel!!.data.user.profile.image.isNotNull()) {
            Glide.with(baseActivity).load(Home.userModel!!.data.user.profile.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .into(binder.ivprofile)

        }


    }

    private fun contentvisibily() {
        if (Home.userModel?.data?.user!!.roles[0].name.equals(Utils.SUPERADMIN)) {
            binder.off.setText(baseActivity.getString(R.string.logout))
            binder.ivcancel.setOnClickListener {
                baseActivity.logout()
            }
            binder.loginbtn.gone()


        } else {
            binder.off.setText(baseActivity.getString(R.string.off_emergency))
            binder.loginbtn.visible()
        }
    }


}