package com.example.opaynhrms.viewmodel

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.databinding.ActivityChangePasswordBinding
import com.example.opaynhrms.databinding.ActivityEditProfileBinding
import com.example.opaynhrms.databinding.ActivityStaffDetailBinding
import com.example.opaynhrms.databinding.ActivityStaffListingBinding
import com.example.opaynhrms.extensions.isNotNull
import com.example.opaynhrms.model.UserListJson
import com.example.opaynhrms.utils.Keys
import com.ieltslearning.base.AppViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*

class StaffDetailViewModel(application: Application) : AppViewModel(application) {
    private lateinit var binder: ActivityStaffDetailBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    val bundle = Bundle()

    fun setBinder(binder: ActivityStaffDetailBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        setclicks()

        settoolbar()
        if (baseActivity.intent.getSerializableExtra(Keys.USERDATA).isNotNull()) {

            val userdata =
                baseActivity.intent.getSerializableExtra(Keys.USERDATA) as UserListJson.Data
            setdata(userdata)

        }
    }

    private fun settoolbar() {
        binder.toolbar.tvtitle.text = "Staff Detail"
    }


    private fun setdata(data: UserListJson.Data) {

    }

    private fun setclicks() {
        binder.toolbar.icmenu.setOnClickListener {
            baseActivity.onBackPressed()
        }

    }

}