package com.example.opaynhrms.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.opaynhrms.R
import com.example.opaynhrms.base.KotlinBaseActivity
import com.example.opaynhrms.base.AppViewModel
import com.example.opaynhrms.databinding.ActivityCommonBinding
import com.example.opaynhrms.fragment.LeaveDetailFragment
import com.example.opaynhrms.utils.Keys


class CommonActivityViewModel(application: Application) : AppViewModel(application),
    View.OnClickListener {
    private lateinit var binder: ActivityCommonBinding
    private lateinit var mContext: Context
    lateinit var baseActivity: KotlinBaseActivity
    var bundle = Bundle()


    fun setBinder(binder: ActivityCommonBinding, baseActivity: KotlinBaseActivity) {
        this.binder = binder
        this.mContext = binder.root.context
        this.baseActivity = baseActivity
        this.binder.viewModel = this
        bundle = (mContext as Activity).intent.extras!!
        setFragment()

    }


    private fun setFragment() {
        when (bundle.getString(Keys.FROM)) {
            mContext.getString(R.string.leavemanagement)->{
                baseActivity.navigateToFragment(LeaveDetailFragment(baseActivity),bundle,false)
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {

        }

    }
}